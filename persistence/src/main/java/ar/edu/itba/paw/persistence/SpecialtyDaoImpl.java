package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.interfaces.dao.SpecialtyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SpecialtyDaoImpl implements SpecialtyDao{
   /* private JdbcTemplate jdbcTemplate;
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
        List<Specialty> list = jdbcTemplate.query("SELECT * FROM specialties WHERE name = ?", ROW_MAPPER, SpecialtyName);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Specialty> getSpecialties() {
        return jdbcTemplate.query("SELECT * FROM specialties", ROW_MAPPER);
    }

    @Override
    public void updateSpecialty(String oldName, String name) {
        jdbcTemplate.update("update specialties set name = ? where name = ?", name, oldName);
    }

    @Override
    public long deleteSpecialty(String name) {
        String deleteQuery = "DELETE FROM specialties WHERE name = ?";
        return jdbcTemplate.update(deleteQuery, name);
    } */

    //Hibernate
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Specialty createSpecialty(String name){
        Specialty specialty = new Specialty(name);
        entityManager.persist(specialty);
        return specialty;
    }

    @Override
    public Specialty getSpecialtyByName(String specialtyName){
        return entityManager.find(Specialty.class,specialtyName);
    }

    @Override
    public List<Specialty> getSpecialties(){
        final TypedQuery<Specialty> query = entityManager.createQuery("from Specialty as spcecialty",Specialty.class);
        final List<Specialty> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public long deleteSpecialty(String name) {
        final TypedQuery<Specialty> query = entityManager.createQuery("delete from Specialty as specialty where specialty.name = :name",Specialty.class);
        query.setParameter("name",name);
        return query.executeUpdate();
    }
}
