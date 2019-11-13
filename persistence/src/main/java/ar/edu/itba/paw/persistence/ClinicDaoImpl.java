package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.ClinicDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClinicDaoImpl implements ClinicDao {
    /*private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

        private final static RowMapper<Clinic> ROW_MAPPER = new RowMapper<Clinic>() {
        @Override
        public Clinic mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Clinic(resultSet.getInt("id"),
                              resultSet.getString("name"),
                              resultSet.getString("address"),
                              new Location(resultSet.getString("location")));
        }
    };

    @Autowired
    public ClinicDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("clinics")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Clinic createClinic(String name, String address, Location location) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("address", address);
        args.put("location", location.getLocationName());
        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Clinic(id.intValue(), name, address, location);
    }

    @Override
    public Clinic getClinicByName(String clinicName) {
        final List<Clinic> list = jdbcTemplate.query("select * from clinics where name = ?",ROW_MAPPER,clinicName);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Clinic> getClinics(){
        return jdbcTemplate.query( "select * from clinics", ROW_MAPPER);
    }

    @Override
    public Clinic getClinicById(int id) {
        final List<Clinic> list = jdbcTemplate.query("select * from clinics where id = ?",ROW_MAPPER,id);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Clinic> getClinicsByLocation(String location) {
        return jdbcTemplate.query("select * from clinics where location = ?",ROW_MAPPER,location);
    }

    @Override
    public boolean clinicExists(String name, String address, String location) {
        final List<Clinic> list = jdbcTemplate.query("select * from clinics where name = ? and address = ? and location = ?",ROW_MAPPER,name,address,location);
        return !list.isEmpty();
    } */

    //Hibernate

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
        return list.isEmpty() ? null : list;
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
        return list.isEmpty() ? null : list;
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
    } */

    //Hibernate

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
        return list.isEmpty() ? null : list;
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
        return list.isEmpty() ? null : list;
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
        jdbcTemplate.update("update clinics set name = ?, address = ?, location = ? where id    = ?", name, address, location, id);
    }

    @Override
    public long deleteClinic(int id) {
        String deleteQuery = "DELETE FROM clinics WHERE id = ?";
        return jdbcTemplate.update(deleteQuery, id);
    }
}
