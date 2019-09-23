package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ClinicDao;
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
import java.util.List;
import java.util.Map;

@Repository
public class ClinicDaoImpl implements ClinicDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

        private final static RowMapper<Clinic> ROW_MAPPER = new RowMapper<Clinic>() {
        @Override
        public Clinic mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Clinic(resultSet.getInt("clinicid"),resultSet.getString("name"), new Location(resultSet.getString("location")));
        }
    };

    @Autowired
    public ClinicDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("clinics")
                .usingGeneratedKeyColumns("clinicid");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS clinics (" +
                "clinicId SERIAL PRIMARY KEY," +
                "name VARCHAR(20) ," +
                "location VARCHAR(60) REFERENCES locations(name)" +
                ")");
    }

    @Override
    public Clinic createClinic(String name, Location location) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("location", location.getLocationName());
        final Number id = jdbcInsert.executeAndReturnKey(args);
        return new Clinic(id.intValue(), name, location);
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
        final List<Clinic> list = jdbcTemplate.query("select * from clinics where clinicId = ?",ROW_MAPPER,id);
        if(list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
