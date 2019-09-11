package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.model.Doctor;
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
                    resultSet.getString("specialty"),
                    resultSet.getString("location"),
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
                "name VARCHAR(60)," +
                "specialty VARCHAR(50)," +
                "location VARCHAR(50)," +
                "license VARCHAR(20) PRIMARY KEY" +
                ")");
    }

    @Override
    public Doctor createDoctor(final String name, final String specialty, final String location, final String license, final String phoneNumber) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        args.put("specialty", specialty);
        args.put("location", location);
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
    public List<Doctor> getDoctorByLocation(String location) {

        final List<Doctor> list = jdbcTemplate.query("select * from doctors where location = ?",ROW_MAPPER,location);
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
    public List<Doctor> getDoctorBySpecialty(String specialty) {
        final List<Doctor> list = jdbcTemplate.query("select * from doctors where specialty = ?",ROW_MAPPER,specialty);
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
    public List<Doctor> getFilteredDoctors(final String location, final String specialty, final String clinic) {

        DoctorQueryBuilder builder = new DoctorQueryBuilder();
        builder.buildQuery(location, specialty, clinic);

        final List<Doctor> list = jdbcTemplate.query(builder.getQuery(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                int i=1;

                if(location!=null){
                    preparedStatement.setString(i,location);
                    i++;
                }
                if(specialty!=null){
                    preparedStatement.setString(i,specialty);
                    i++;
                }
                if(clinic!=null){
                    preparedStatement.setString(i,clinic);
                }

            }
        }, ROW_MAPPER);
        return ( list.isEmpty() ? null : list );
    }
}
