package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>() {

        @Override public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("name"),rs.getString("id"),rs.getString("password"),
                    rs.getString("healthInsurance"),rs.getString("email"));
        }
    };

    @Autowired
    public UserDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users");

        jdbcTemplate.execute( "CREATE TABLE IF NOT EXISTS users ("+
                "id VARCHAR(8) PRIMARY KEY,"+
                "name VARCHAR(20),"+
                "password VARCHAR(15),"+
                "email VARCHAR(20),"+
                "healthInsurance VARCHAR(10)"+
                ");"
        );
    }

    @Override
    public User createUser(String id, String name, String password, String email, String healthInsurance) {
        final Map<String, Object> args = new HashMap<>();
        args.put("id",id);
        args.put("name",name);
        args.put("password",password);
        args.put("email",email);
        args.put("healthInsurance",healthInsurance);

        int result;
        result = jdbcInsert.execute(args);
        return new User(name,id,password,healthInsurance,email);
    }
}
