package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DoctorDaoImpl implements DoctorDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Doctor> ROW_MAPPER = new RowMapper<Doctor>() {
        @Override
        public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Doctor(resultSet.getString("name"),
                   new Specialty(resultSet.getString("specialty")),
                   new Location(resultSet.getString("location")),
                    resultSet.getString("license"),
                    resultSet.getString("phoneNumber"));
        }
    };

    @Autowired
    public DoctorDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                         .withTableName("doctors");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS doctors (" +
                "license VARCHAR(20) PRIMARY KEY," +
                "specialty VARCHAR(50) REFERENCES specialties(name)," +
                "location VARCHAR(50) REFERENCES locations(name)," +
                "name VARCHAR(60)," +
                "phoneNumber VARCHAR(20)" +
                ")");
    }

    @Override
    public Doctor createDoctor(final String name, final Specialty specialty, final Location location, final String license, final String phoneNumber) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("specialty", specialty.getSpecialtyName());
        args.put("location", location.getLocationName());
        args.put("license", license);
        args.put("phoneNumber", phoneNumber);
        int result;

        result = jdbcInsert.execute(args);

        return new Doctor(name, specialty, location, license, phoneNumber);
    }

    @Override
    public List<Doctor> getDoctors() {
        final List<Doctor> list = jdbcTemplate.query("select * from doctors",ROW_MAPPER);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Doctor> getDoctorByLocation(Location location) {

        final List<Doctor> list = jdbcTemplate.query("select * from doctors where location = ?",ROW_MAPPER,location.getLocationName());
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Doctor> getDoctorByName(String name) {
        final List<Doctor> list = jdbcTemplate.query("select * from doctors where name = ?",ROW_MAPPER,name);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<Doctor> getDoctorBySpecialty(Specialty specialty) {
        final List<Doctor> list = jdbcTemplate.query("select * from doctors where specialty = ?",ROW_MAPPER,specialty.getSpecialtyName());
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public Doctor getDoctorByLicense(String license) {
        final List<Doctor> list = jdbcTemplate.query("select * from doctors where license = ?",ROW_MAPPER,license);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Doctor> getFilteredDoctors(final Location location, final Specialty specialty, final String clinic) {

        DoctorQueryBuilder builder = new DoctorQueryBuilder();
        builder.buildQuery(location.getLocationName(), specialty.getSpecialtyName(), clinic);

        final List<Doctor> list = jdbcTemplate.query(builder.getQuery(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                int i=1;

                if(location!=null){
                    preparedStatement.setString(i,location.getLocationName());
                    i++;
                }
                if(specialty!=null){
                    preparedStatement.setString(i,specialty.getSpecialtyName());
                    i++;
                }
            }
        }, ROW_MAPPER);
        return ( list.isEmpty() ? null : list );
    }
}
