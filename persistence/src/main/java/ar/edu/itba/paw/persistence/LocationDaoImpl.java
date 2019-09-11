package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.LocationDao;
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
import java.util.List;
import java.util.Map;

@Repository
public class LocationDaoImpl implements LocationDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

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

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS locations (" +
                "name VARCHAR(30) PRIMARY KEY" +
                ")");
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
        return jdbcTemplate.queryForObject("select * from locations where name = ?", Location.class, locationName);
    }

    @Override
    public List<Location> getLocations(){
        return jdbcTemplate.query( "select * from locations", ROW_MAPPER);
    }
}
