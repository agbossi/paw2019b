package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.PrepaidToClinicDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;
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
public class PrepaidToClinicDaoImpl implements PrepaidToClinicDao {

   /* private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<PrepaidToClinic> ROW_MAPPER = new RowMapper<PrepaidToClinic>() {
        @Override
        public PrepaidToClinic mapRow(ResultSet resultSet, int i) throws SQLException {
            return new PrepaidToClinic(new Clinic(resultSet.getInt("clinicid"),
                                resultSet.getString("name"),
                                resultSet.getString("address"),
                                new Location(resultSet.getString("location"))),
                    new Prepaid(resultSet.getString("prepaid")));
        }
    };

    @Autowired
    public PrepaidToClinicDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("clinicPrepaids");
    }

    @Override
    public List<PrepaidToClinic> getPrepaidToClinics() {
        return jdbcTemplate.query(
                "select clinicPrepaids.clinicid, clinics.name, clinics.address, clinics.location, clinicPrepaids.prepaid from " +
                "(clinicPrepaids join clinics on clinicPrepaids.clinicid = clinics.id)", ROW_MAPPER);
    }

    @Override
    public PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic) {
        final Map<String, Object> args = new HashMap<>();
        args.put("prepaid", prepaid.getName());
        args.put("clinicid", clinic.getId());
        int result;

        result = jdbcInsert.execute(args);

        return new PrepaidToClinic(clinic,prepaid);
    }

    @Override
    public boolean clinicHasPrepaid(String prepaid, int clinic) {
        List<PrepaidToClinic> list = jdbcTemplate.query("select location,prepaid,clinicPrepaids.clinicid,address,name from clinicPrepaids join clinics on clinics.id = clinicPrepaids.clinicid where prepaid = ? and clinicid = ?",ROW_MAPPER,prepaid,clinic);
        return !list.isEmpty();
    }

    @Override
    public long deletePrepaidFromClinic(String prepaid, int clinic) {
        String deleteQuery = "DELETE FROM clinicPrepaids WHERE prepaid = ? AND clinicid = ?";
        return jdbcTemplate.update(deleteQuery, prepaid, clinic);
    } */

    //Hibernate
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PrepaidToClinic> getPrepaidToClinics(){
        //en el join es p.clinic o p.id?
        TypedQuery<PrepaidToClinic> query = entityManager.createQuery("from PrepaidToClinic as p inner join p.clinic",PrepaidToClinic.class);
        List<PrepaidToClinic> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic){
        PrepaidToClinic prepaidToClinic = new PrepaidToClinic(clinic, prepaid);
        entityManager.persist(prepaidToClinic);
        return prepaidToClinic;
    }

    @Override
    public boolean clinicHasPrepaid(String prepaid, int clinic){
        TypedQuery<PrepaidToClinic> query = entityManager.createQuery("from PrepaidToClinic as p inner join p.clinic as pc where pc.id = :clinic and p.prepaid = :prepaid",PrepaidToClinic.class);
        query.setParameter("clinic",clinic);
        query.setParameter("prepaid",prepaid);
        List<PrepaidToClinic> list = query.getResultList();
        return !list.isEmpty();
    }

    @Override
    public long deletePrepaidFromClinic(String prepaid, int clinic) {
        TypedQuery<PrepaidToClinic> query = entityManager.createQuery("delete from PrepaidToClinic as p " +
                "where p.prepaid =: prepaid and p.clinic =: clinic",PrepaidToClinic.class);
        query.setParameter("clinic",clinic);
        query.setParameter("prepaid",prepaid);
        return query.executeUpdate();
    }
}
