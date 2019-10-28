package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserDaoImpl implements UserDao {

    /* private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>() {

        @Override public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("password"),
                            rs.getString("email"));
        }
    };

    @Autowired
    public UserDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
    }

    @Override
    public User createUser(String firstName,String lastName, String password, String email) {
        final Map<String, Object> args = new HashMap<>();
        args.put("firstName",firstName);
        args.put("lastName",lastName);
        args.put("password",password);
        args.put("email",email);

        int result;
        result = jdbcInsert.execute(args);
        return new User(firstName,lastName,password,email);
    }

    @Override
    public User findUserByEmail(String email) {
        final List<User> list = jdbcTemplate.query("select * from users where email = ?",ROW_MAPPER,email);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    //esta es necesaria?
    @Override
    public void changePassword(String password,String email){
        jdbcTemplate.update("update users set password = ? where email = ?",password,email);
    }

    @Override
    public void updateUser(String email, Map<String, String> args) {
        if(args.containsKey("password")) {
            jdbcTemplate.update("update users set firstName = ?, lastName = ?, password = ? where email = ?",
                    args.get("firstName"),
                    args.get("lastName"),
                    args.get("password"),
                    email);
        }
        else {
            jdbcTemplate.update("update users set firstName = ?, lastName = ? where email = ?",
                    args.get("firstName"),
                    args.get("lastName"),
                    email);
        }
    } */

    //Hibernate

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(String firstName,String lastName, String password, String email){
        final User user = new User(firstName,lastName,password,email);
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findUserByEmail(String email){
        return entityManager.find(User.class,email);
    }

    @Override
    public void updateUser(String email, Map<String, String> args){
        final TypedQuery<User> query = entityManager.createQuery("update User user set user.firstName =: firstName, user.lastName =: lastName where user.email =: email", User.class);
        query.setParameter("email",email);
        query.setParameter("firstName",args.get("firstName"));
        query.setParameter("lastName",args.get("lastName"));
        query.executeUpdate();
    }

    @Override
    public long deleteUser(String email) {
        String deleteQuery = "DELETE FROM users WHERE email = ?";
        return jdbcTemplate.update(deleteQuery, email);
    }

}
