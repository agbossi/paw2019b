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
            return new Patient(rs.getString("id"),
                    rs.getString("prepaid"),
                    rs.getString("prepaidNumber"));
        }
    };

    @Autowired
    public PatientDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("patients");

        jdbcTemplate.execute( "CREATE TABLE IF NOT EXISTS patients ("+
                "id VARCHAR(9) PRIMARY KEY REFERENCES users(id),"+
                "prepaid VARCHAR(20),"+
                "prepaidNumber varchar(20)"+
                ");"
        );
    }


    @Override
    public Patient create(String id, String prepaid, String prepaidNumber) {
        final Map<String, Object> args = new HashMap<>();
        args.put("id",id);
        args.put("prepaid",prepaid);
        args.put("prepaidNumber",prepaidNumber);

        int result;
        result = jdbcInsert.execute(args);
        return new Patient(id,prepaid,prepaidNumber);
    }

    @Override
    public Patient getPatientById(String id) {
        List<Patient> patient = jdbcTemplate.query("select * from patients where id = ?", ROW_MAPPER, id);
        if(patient.isEmpty()) {
            return null;
        }
        return patient.get(0);
    }
}
