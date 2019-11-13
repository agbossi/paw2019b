package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.AdminDao;
import ar.edu.itba.paw.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDaoImpl implements AdminDao {

  /*  private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Admin> ROW_MAPPER = new RowMapper<Admin>() {
        @Override
        public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Admin(resultSet.getString("email"));
        }
    };

    @Autowired
    public AdminDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("admins");
    }

    @Override
    public Admin getAdmin(String email) {
        final List<Admin> list = jdbcTemplate.query("select * from admins where email = ? ",ROW_MAPPER,email);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean isAdmin(String email) {
        return getAdmin(email) == null ? false : true; */

  @PersistenceContext
    EntityManager entityManager;

    @Override
    public Admin getAdmin(String email){
        return entityManager.find(Admin.class,email);
    }

    @Override
    public boolean isAdmin(String email){
        return getAdmin(email) != null;
    }
}

