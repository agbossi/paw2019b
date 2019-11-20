package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.PaginationDao;
import ar.edu.itba.paw.interfaces.dao.PrepaidToClinicDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PrepaidToClinicDaoImpl implements PrepaidToClinicDao {

    @PersistenceContext
    EntityManager entityManager;

    private final static int MAX_PREPAID_TO_CLINICS_PER_PAGE = 20;

    @Override
    public List<PrepaidToClinic> getPrepaidToClinics(){
        //en el join es p.clinic o p.id?
        TypedQuery<PrepaidToClinic> query = entityManager.createQuery("from PrepaidToClinic as p",PrepaidToClinic.class);
        List<PrepaidToClinic> list = query.getResultList();
        return list;
    }

    @Override
    public List<PrepaidToClinic> getPaginatedObjects(int page){
        TypedQuery<PrepaidToClinic> query = entityManager.createQuery("from PrepaidToClinic as p ORDER BY " +
                        "p.prepaid.name, p.clinic.name, clinic.location.name",
                PrepaidToClinic.class);
        List<PrepaidToClinic> list = query.setFirstResult(page * MAX_PREPAID_TO_CLINICS_PER_PAGE)
                .setMaxResults(MAX_PREPAID_TO_CLINICS_PER_PAGE)
                .getResultList();
        return list;
    }

    @Override
    public int maxAvailablePage() {
        return (int) (Math.ceil(( ((double)getPrepaidToClinics().size()) / (double)MAX_PREPAID_TO_CLINICS_PER_PAGE)));
    }

    @Override
    public PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic){
        PrepaidToClinic prepaidToClinic = new PrepaidToClinic(clinic, prepaid);
        entityManager.persist(prepaidToClinic);
        return prepaidToClinic;
    }

    @Override
    public boolean clinicHasPrepaid(String prepaid, int clinic){
        TypedQuery<PrepaidToClinic> query = entityManager.createQuery("from PrepaidToClinic as p where p.clinic.id = :clinic and p.prepaid.name = :prepaid",PrepaidToClinic.class);
        query.setParameter("clinic",clinic);
        query.setParameter("prepaid",prepaid);
        List<PrepaidToClinic> list = query.getResultList();
        return !list.isEmpty();
    }

    @Override
    public long deletePrepaidFromClinic(String prepaid, int clinic) {
        Query query = entityManager.createQuery("delete from PrepaidToClinic as p " +
                "where p.prepaid = :prepaid and p.clinic.id = :clinic");
        query.setParameter("clinic",clinic);
        query.setParameter("prepaid",prepaid);
        return query.executeUpdate();
    }
}
