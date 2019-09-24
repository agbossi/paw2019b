package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PatientDao;
import ar.edu.itba.paw.model.Patient;
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
public class PatientDaoImpl implements PatientDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Patient> ROW_MAPPER = new RowMapper<Patient>() {

        @Override public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Patient(rs.getString("email"),
                    rs.getString("prepaid"),
                    rs.getString("prepaidNumber"));
        }
    };

    @Autowired
    public PatientDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("patients");
    }


    @Override
    public Patient create(String email, String prepaid, String prepaidNumber) {
        final Map<String, Object> args = new HashMap<>();
        args.put("email",email);
        args.put("prepaid",prepaid);
        args.put("prepaidNumber",prepaidNumber);

        int result;
        result = jdbcInsert.execute(args);
        return new Patient(email,prepaid,prepaidNumber);
    }

    @Override
    public Patient getPatientById(String email) {
        List<Patient> patient = jdbcTemplate.query("select * from patients where email = ?", ROW_MAPPER, email);
        if(patient.isEmpty()) {
            return null;
        }
        return patient.get(0);
    }
}
