package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PrepaidDao;
import ar.edu.itba.paw.model.Prepaid;
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
public class PrepaidDaoImpl implements PrepaidDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Prepaid> ROW_MAPPER = new RowMapper<Prepaid>() {
        @Override
        public Prepaid mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Prepaid(resultSet.getString("name"));
        }
    };

    @Autowired
    public PrepaidDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("prepaids");
    }

    @Override
    public Prepaid createPrepaid(String name) {
        final Map<String, Object> args = new HashMap<>();
        args.put("name", name);
        int result;
        result = jdbcInsert.execute(args);

        return new Prepaid(name);
    }

    @Override
    public Prepaid getPrepaidByName(String PrepaidName) {
        return jdbcTemplate.queryForObject("SELECT * FROM prepaids WHERE name = ?", Prepaid.class, PrepaidName);
    }

    @Override
    public List<Prepaid> getPrepaids() {
        return jdbcTemplate.query("SELECT * FROM prepaids", ROW_MAPPER);
    }
}
