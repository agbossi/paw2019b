package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.DoctorClinicKey;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DoctorClinicDaoImpl implements DoctorClinicDao {

    @PersistenceContext
    private EntityManager entityManager;

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
                " where dc.clinic.id = :id",DoctorClinic.class);
        query.setParameter("id",clinic);
        List<DoctorClinic> list = query.getResultList();
        return list;
    }

    @Override
    public DoctorClinic getDoctorInClinic(String license, int clinic){
        return entityManager.find(DoctorClinic.class,new DoctorClinicKey(license,clinic));
    }

    //solo para que no explote la clase
    @Override
    public List<DoctorClinic> getClinicsWithDoctor(String doctor){
        Doctor d = entityManager.find(Doctor.class,doctor);
        return getDoctorClinicsForDoctor(d);
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
        else if(consultPrice > 0){
            query.setParameter("consultPrice",consultPrice);
        }
        List<DoctorClinic> list = query.getResultList();
        return list;
    }

    @Override
   public long deleteDoctorClinic(String license, int clinicid) {
        //String deleteQuery = "DELETE FROM doctorClinics WHERE doctorLicense = ? and clinicid = ?";
        //return jdbcTemplate.update(deleteQuery, license, clinicid);
        Query query = entityManager.createQuery("delete from DoctorClinic as docCli where docCli.doctor.license = :license" +
                " and docCli.clinic.id = :id");
        query.setParameter("license",license);
        query.setParameter("id",clinicid);
        return query.executeUpdate();
    }

}


