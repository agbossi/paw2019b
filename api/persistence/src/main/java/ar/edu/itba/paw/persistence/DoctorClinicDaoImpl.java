package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.DoctorClinicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorClinicDaoImpl implements DoctorClinicDao {

    @Autowired
    private DoctorDao doctorDao;

    @PersistenceContext
    private EntityManager entityManager;

    private static final int MAX_DOCTORS_CLINICS_PER_PAGE = 6;

    @Override
    public DoctorClinic createDoctorClinic(Doctor doctor, Clinic clinic, int consultPrice){
        DoctorClinic doctorClinic = new DoctorClinic(doctor,clinic,consultPrice);
        entityManager.persist(doctorClinic);
        return doctorClinic;
    }

    @Override
    public List<DoctorClinic> getDoctorClinics(){
        TypedQuery<DoctorClinic> query = entityManager.createQuery("from DoctorClinic as dc",DoctorClinic.class);
        List<DoctorClinic> list = query.getResultList();
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor) {
        TypedQuery<DoctorClinic> query = entityManager.createQuery("from DoctorClinic as dc "  +
                                         " where dc.doctor.license = :doctorLicense",DoctorClinic.class);
        query.setParameter("doctorLicense",doctor.getLicense());
        List<DoctorClinic> list = query.getResultList();
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorsInClinic(int clinic){
        TypedQuery<DoctorClinic> query = entityManager.createQuery("from DoctorClinic as dc"  +
                " where dc.clinic.id = :id", DoctorClinic.class);
        query.setParameter("id",clinic);
        List<DoctorClinic> list = query.getResultList();
        return list;
    }

    @Override
    public DoctorClinic getDoctorInClinic(String license, int clinic){
        return entityManager.find(DoctorClinic.class, new DoctorClinicKey(license,clinic));
    }

    @Override
    public List<DoctorClinic> getClinicsWithDoctor(String doctor){
        Doctor d = entityManager.find(Doctor.class,doctor);
        return getDoctorClinicsForDoctor(d);
    }

    @Override
    public List<String> getIdsForSearch(final Location location, final Specialty specialty,
                                        final String firstName, final String lastName, final Prepaid prepaid,
                                        final int consultPrice, int page) {
        DoctorQueryBuilder builder = new DoctorQueryBuilder();
        builder.buildNativeQuery(location.getLocationName(), specialty.getSpecialtyName(), firstName, lastName, prepaid.getName(), consultPrice);

        Query idsQuery = entityManager.createNativeQuery(builder.getQuery());
        //TypedQuery<DoctorClinicKey> idsQuery = entityManager.createQuery(builder.getQuery(),DoctorClinicKey.class);
        Map<Integer, Object> positionalParameters = builder.getPositionalParameters();
        for(int i = 0; i < positionalParameters.size(); i++) {
            idsQuery.setParameter(i, positionalParameters.get(i));
        }

        @SuppressWarnings("unchecked")
        List<String> ids = page >= 0 ? idsQuery.setFirstResult(page * MAX_DOCTORS_CLINICS_PER_PAGE)
                .setMaxResults(MAX_DOCTORS_CLINICS_PER_PAGE).getResultList() : idsQuery.getResultList();
        return ids;
    }

    @Override
    public List<Doctor> getFilteredDoctors(final Location location, final Specialty specialty,
                                           final String firstName, final String lastName, final Prepaid prepaid,
                                           final int consultPrice, int page){
        List<String> ids = this.getIdsForSearch(location, specialty,
                firstName, lastName, prepaid, consultPrice, page);
        return doctorDao.getDoctorsByLicenses(ids);
    }

    @Override
    public List<DoctorClinic> getFilteredDoctors(final Location location, final Specialty specialty,
                                                 final String firstName, final String lastName, final Prepaid prepaid,
                                                 final int consultPrice){
        DoctorQueryBuilder builder = new DoctorQueryBuilder();
        builder.buildQuery(location.getLocationName(), specialty.getSpecialtyName(), firstName, lastName, prepaid.getName(), consultPrice);
        TypedQuery<DoctorClinic> query = entityManager.createQuery(builder.getQuery(),DoctorClinic.class);
        if(!location.getLocationName().equals("")){
            query.setParameter("location",location.getLocationName());
        }
        if(!specialty.getSpecialtyName().equals("")){
            query.setParameter("specialty",specialty.getSpecialtyName());
        }
        if(!firstName.equals("")){
            query.setParameter("firstName",firstName);
        }
        if(!lastName.equals("")){
            query.setParameter("lastName",lastName);
        }
        if(!prepaid.getName().equals("")){
            query.setParameter("prepaid",prepaid.getName());
        }
        else if( consultPrice > 0){
            query.setParameter("consultPrice",consultPrice);
        }
        List<DoctorClinic> list = query.getResultList();
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorClinicPaginatedByList(Doctor doctor, int page) {

        TypedQuery<DoctorClinicKey> idsQuery = entityManager.createQuery("select dc.doctorClinicKey from DoctorClinic as dc "  +
                " where dc.doctor.license = :doctorLicense",DoctorClinicKey.class);
        idsQuery.setParameter("doctorLicense", doctor.getLicense());

        List<DoctorClinicKey> ids = idsQuery.setFirstResult(page * MAX_DOCTORS_CLINICS_PER_PAGE)
                .setMaxResults(MAX_DOCTORS_CLINICS_PER_PAGE)
                .getResultList();

        if(ids.isEmpty()) {
            return Collections.emptyList();
        }

        TypedQuery<DoctorClinic> query = entityManager.createQuery("from DoctorClinic as dc where " +
                "dc.doctorClinicKey IN (:filteredDoctorClinic)", DoctorClinic.class);
        query.setParameter("filteredDoctorClinic", ids);
        return query.getResultList();
    }

    @Override
    public int maxPageAvailable() {
        return MAX_DOCTORS_CLINICS_PER_PAGE;
    }

    @Override
    public int maxPageAvailableForDoctor(Doctor doctor) {
        return (int) (Math.ceil(( ((double)getDoctorClinicsForDoctor(doctor).size()) / (double)MAX_DOCTORS_CLINICS_PER_PAGE)));
    }

    @Override
    public void editPrice(DoctorClinic dc, int price) {
        Query query = entityManager.createQuery("update DoctorClinic dc set dc.consultPrice = :newPrice " +
                "where dc.clinic.id = :id and dc.doctor.license = :license");
        query.setParameter("newPrice", price);
        query.setParameter("id", dc.getClinic().getId());
        query.setParameter("license", dc.getDoctor().getLicense());
        query.executeUpdate();
    }

    @Override
   public long deleteDoctorClinic(String license, int clinicid) {
        Query query = entityManager.createQuery("delete from DoctorClinic as docCli where docCli.doctor.license = :license" +
                " and docCli.clinic.id = :id");
        query.setParameter("license",license);
        query.setParameter("id",clinicid);
        return query.executeUpdate();
    }

}


