package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.model.Clinic;
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
            return new Doctor(resultSet.getString("doctorName"),
                   new Specialty(resultSet.getString("specialty")),
                    resultSet.getString("license"),
                    resultSet.getString("phoneNumber")

            );
        }
    };

    @Autowired
    public DoctorDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                         .withTableName("doctors");
    }

    @Override
    public Doctor createDoctor(final String name, final Specialty specialty, final String license, final String phoneNumber) {
        final Map<String, Object> args = new HashMap<>();
        args.put("doctorName", name);
        args.put("specialty", specialty.getSpecialtyName());
        args.put("license", license);
        args.put("phoneNumber", phoneNumber);
        int result;

        result = jdbcInsert.execute(args);

        return new Doctor(name, specialty, license, phoneNumber);
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
    public List<Doctor> getDoctorByName(String name) {
        final List<Doctor> list = jdbcTemplate.query("select * from doctors where doctorName = ?",ROW_MAPPER,name);
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

        return null;
    }
}
