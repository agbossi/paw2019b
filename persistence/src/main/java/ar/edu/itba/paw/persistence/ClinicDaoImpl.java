package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.ClinicDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClinicDaoImpl implements ClinicDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Clinic createClinic(String name, String address, Location location){
        Clinic clinic = new Clinic();
        clinic.setLocation(location);
        clinic.setName(name);
        clinic.setAddress(address);
        entityManager.persist(clinic);
        return clinic;
    }

    @Override
    public Clinic getClinicByName(String clinicName){
        TypedQuery<Clinic> query = entityManager.createQuery("from Clinic as clinic where clinic.name = :name",Clinic.class);
        query.setParameter("name",clinicName);
        List<Clinic> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Clinic> getClinics(){
        TypedQuery<Clinic> query = entityManager.createQuery("from Clinic as clinic",Clinic.class);
        List<Clinic> list = query.getResultList();
        return list;
    }

    @Override
    public Clinic getClinicById(int id) {
        return entityManager.find(Clinic.class,id);
    }

    @Override
    public List<Clinic> getClinicsByLocation(Location location){
        TypedQuery<Clinic> query = entityManager.createQuery("from Clinic as clinic where clinic.location.name = :location",Clinic.class);
        query.setParameter("location",location.getLocationName());
        List<Clinic> list = query.getResultList();
        return list;
    }
    @Override
    public boolean clinicExists(String name, String address, Location location){
        TypedQuery<Clinic> query = entityManager.createQuery("from Clinic as clinic" +
                " where clinic.location.name = :location and clinic.name = :name and clinic.address = :address",Clinic.class);
        query.setParameter("location",location.getLocationName());
        query.setParameter("name",name);
        query.setParameter("address",address);
        List<Clinic> list = query.getResultList();
        return !list.isEmpty();
    }

    @Override
    public void updateClinic(int id, String name, String address, String location) {
        //jdbcTemplate.update("update clinics set name = ?, address = ?, location = ? where id    = ?", name, address, location, id);
        final Query query = entityManager.createQuery("update Clinic clinic set clinic.name = :name, clinic.location.name = :location, " +
                "clinic.address = :address where clinic.id = :id");
        query.setParameter("id",id);
        query.setParameter("name",name);
        query.setParameter("location",location);
        query.setParameter("address",address);
        query.executeUpdate();
    }


    @Override
    public long deleteClinic(int id) {
        //String deleteQuery = "DELETE FROM clinics WHERE id = ?";
        //return jdbcTemplate.update(deleteQuery, id);
        Query query = entityManager.createQuery("delete from Clinic as clinic where clinic.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
