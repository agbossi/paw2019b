package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.LocationDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class LocationDaoImpl implements LocationDao {
    /*private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    ClinicDaoImpl clinicDao;

    private final static RowMapper<Location> ROW_MAPPER = new RowMapper<Location>() {
        @Override
        public Location mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Location(resultSet.getString("name"));
        }
    };

    @Autowired
    public LocationDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("locations");
    }

    @Override
    public Location createLocation(String name) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        int result;

        result = jdbcInsert.execute(args);

        return new Location(name);
    }

    @Override
    public Location getLocationByName(String locationName) {
        List<Location> list = jdbcTemplate.query("select * from locations where name = ?", ROW_MAPPER, locationName);
        if(!list.isEmpty()){
            List<Clinic> clinics = clinicDao.getClinicsByLocation(locationName);
            Location location = list.get(0);
            location.setClinicsInLocation(clinics);
            return location;
        }
        return null;
    }

    @Override
    public List<Location> getLocations(){
        List<Clinic> clinics;
        List<Location> locations = jdbcTemplate.query( "select * from locations", ROW_MAPPER);
        if(locations != null){
            for (Location l:locations) {
                clinics = clinicDao.getClinicsByLocation(l.getLocationName());
                if(clinics != null){
                    l.setClinicsInLocation(clinics);
                    clinics.clear();
                }
            }
            return locations;
        }
        return new ArrayList<>();
    }

    @Override
    public void updateLocation(String oldName, String name) {
        jdbcTemplate.update("update locations set name = ? where name = ?", name, oldName);
    }

    @Override
    public long deleteLocation(String name) {
        String deleteQuery = "DELETE FROM locations WHERE name = ?";
        return jdbcTemplate.update(deleteQuery, name);
    } */

    //Hibernate
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Location getLocationByName(String locationName){
        return entityManager.find(Location.class,locationName);
    }

    @Override
    public Location createLocation(String name){
        Location location = new Location(name);
        entityManager.persist(location);
        return location;
    }

    @Override
    public List<Location> getLocations(){
        TypedQuery<Location> query = entityManager.createQuery("from Location as location",Location.class);
        List<Location> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public long deleteLocation(String name){
        Query query = entityManager.createQuery("delete from Location as location where location.name = :name");
        query.setParameter("name",name);
        return query.executeUpdate();
    }
}
