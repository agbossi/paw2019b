package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.LocationDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class LocationDaoImpl implements LocationDao {
    private JdbcTemplate jdbcTemplate;
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
        Location location = jdbcTemplate.queryForObject("select * from locations where name = ?", Location.class, locationName);
        List<Clinic> clinics = clinicDao.getClinicsByLocation(locationName);
        location.setClinicsInLocation(clinics);
        return location;
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
        return null;
    }
}
