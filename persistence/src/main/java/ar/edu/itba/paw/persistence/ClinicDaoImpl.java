package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ClinicDao;
import ar.edu.itba.paw.model.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClinicDaoImpl implements ClinicDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

        private final static RowMapper<Clinic> ROW_MAPPER = new RowMapper<Clinic>() {
        @Override
        public Clinic mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Clinic(resultSet.getString("name"), resultSet.getString("city"), resultSet.getInt("consultPrice"));
        }
    };

    @Autowired
    public ClinicDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("clinics");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS clinics (" +
                "name VARCHAR(20) PRIMARY KEY," +
                "consultPrice INTEGER" +
                "city VARCHAR(60)" +
                ")");
    }

    @Override
    public Clinic getClinicByName(String clinic_name) {
        return jdbcTemplate.queryForObject("select * from clinics where name = ?", Clinic.class, clinic_name);
    }

    @Override
    public List<Clinic> getClinics(){
        return jdbcTemplate.query( "select * from clinics", ROW_MAPPER);
    }
}
