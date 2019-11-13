package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.PrepaidDao;
import ar.edu.itba.paw.model.Prepaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class PrepaidDaoImpl implements PrepaidDao {
  /*  private JdbcTemplate jdbcTemplate;
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
        List<Prepaid> list = jdbcTemplate.query("SELECT * FROM prepaids WHERE name = ?", ROW_MAPPER, PrepaidName);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Prepaid> getPrepaids() {
        return jdbcTemplate.query("SELECT * FROM prepaids", ROW_MAPPER);
    }

    @Override
    public void updatePrepaid(String oldName, String name) {
        jdbcTemplate.update("update prepaids set name = ? where name = ?", name, oldName);
    }

    @Override
    public long deletePrepaid(String name) {
        String deleteQuery = "DELETE FROM prepaids WHERE name = ?";
        return jdbcTemplate.update(deleteQuery, name);
    } */
    //Hibernate

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Prepaid createPrepaid(String name){
        Prepaid prepaid = new Prepaid(name);
        entityManager.persist(prepaid);
        return prepaid;
    }

    @Override
    public Prepaid getPrepaidByName(String prepaidName){
        return entityManager.find(Prepaid.class,prepaidName);
    }

    @Override
    public List<Prepaid> getPrepaids(){
        TypedQuery<Prepaid> query = entityManager.createQuery("from Prepaid as prepaid",Prepaid.class);
        List<Prepaid> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public long deletePrepaid(String name){
        Query query = entityManager.createQuery("delete from Prepaid as prepaid where prepaid.name = :name");
        query.setParameter("name",name);
        return query.executeUpdate();
    }
}
