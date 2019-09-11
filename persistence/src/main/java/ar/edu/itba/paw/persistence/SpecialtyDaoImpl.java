package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.interfaces.SpecialtyDao;
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
public class SpecialtyDaoImpl implements SpecialtyDao{
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Specialty> ROW_MAPPER = new RowMapper<Specialty>() {
        @Override
        public Specialty mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Specialty(resultSet.getString("name"));
        }
    };

    @Autowired
    public SpecialtyDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("specialties");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS specialties (" +
                "name VARCHAR(30) PRIMARY KEY" +
                ")");
    }

    @Override
    public Specialty createSpecialty(String name) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        int result;
        result = jdbcInsert.execute(args);

        return new Specialty(name);
    }

    @Override
    public Specialty getSpecialtyByName(String SpecialtyName) {
        return jdbcTemplate.queryForObject("SELECT * FROM specialties WHERE name = ?", Specialty.class, SpecialtyName);
    }

    @Override
    public List<Specialty> getSpecialties() {
        return jdbcTemplate.query("SELECT * FROM specialties", ROW_MAPPER);
    }
}
