package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.PatientDao;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PatientDaoImpl implements PatientDao {

   /* private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Patient> ROW_MAPPER = new RowMapper<Patient>() {

        @Override
        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Patient(rs.getString("email"),
                    rs.getString("id"),
                    rs.getString("prepaid"),
                    rs.getString("prepaidNumber"),
                    rs.getString("firstName"),
                    rs.getString("lastName"));
        }
    };

    @Autowired
    public PatientDaoImpl(final DataSource ds) {

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("patients");
    }


    @Override
    public Patient create(String id, String prepaid, String prepaidNumber, User user) {
        final Map<String, Object> args = new HashMap<>();
        args.put("email", user.getEmail());
        args.put("id", id);
        args.put("prepaid", prepaid);
        args.put("prepaidNumber", prepaidNumber);

        int result;
        result = jdbcInsert.execute(args);
        return new Patient(user.getEmail(), id, prepaid, prepaidNumber, user.getFirstName(), user.getLastName());
    }

    @Override
    public Patient getPatientByEmail(String email) {
        List<Patient> patient = jdbcTemplate.query("select * from patients natural join users where email = ?", ROW_MAPPER, email);
        if (patient.isEmpty()) {
            return null;
        }
        return patient.get(0);
    }

    @Override
    public void updatePatient(String email, Map<String, String> args) {
        if (args.containsKey("id")) {
            jdbcTemplate.update("update patients set id = ? where email = ?", args.get("id"), email);
        }
        if (args.containsKey("prepaid")) {
            jdbcTemplate.update("update patients set prepaid = ? where email = ?", args.get("prepaid"), email);
        }
        if (args.containsKey("prepaidNumber")) {
            jdbcTemplate.update("update patients set prepaidNumber = ? where email = ?", args.get("prepaidNumber"), email);
        }
    } */

    //Hibernate

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Patient create(String id, String prepaid, String prepaidNumber, User user) {

        Patient patient = new Patient(id, prepaid, prepaidNumber, user);
        entityManager.persist(patient);
        return patient;
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return entityManager.find(Patient.class, email);
    }

    @Override
    public void updatePatient(String email, Map<String, String> args) {
        Query query;
        if (args.containsKey("id")) {
            query = entityManager.createQuery("update Patient as patient set id = :id where patient.user.email = :email");
            query.setParameter("email",email);
            query.setParameter("id", args.get("id"));
            query.executeUpdate();
        }
        if (args.containsKey("prepaid")) {
            query = entityManager.createQuery("update Patient as patient set prepaid = :prepaid where patient.user.email = :email");
            query.setParameter("prepaid", args.get("prepaid"));
            query.setParameter("email",email);
            query.executeUpdate();
        }
        if (args.containsKey("prepaidNumber")) {
            query = entityManager.createQuery("update Patient as patient set prepaidNumber = :prepaidNumber where patient.user.email = :email");
            query.setParameter("prepaidNumber", args.get("prepaidNumber"));
            query.setParameter("email",email);
            query.executeUpdate();
        }
    }
}