package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.ImageDao;
import ar.edu.itba.paw.model.Image;
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
public class ImageDaoImpl implements ImageDao {

   /* private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Image> ROW_MAPPER = new RowMapper<Image>() {
        @Override
        public Image mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Image(resultSet.getLong("id"),
                             resultSet.getString("doctor"),
                             resultSet.getBinaryStream("image"));
        }
    };

    @Autowired
    public ImageDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("images")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long createProfileImage(byte[] image, String doctor) {
        Map<String, Object> args = new HashMap<>();
        args.put("doctor", doctor);
        args.put("image", image);
        Number id = jdbcInsert.executeAndReturnKey(args);
        return id.longValue();
    }

    @Override
    public Image getProfileImage(String doctor) {
        final List<Image> list = jdbcTemplate.query("SELECT * FROM images WHERE doctor = ?",ROW_MAPPER,doctor);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public long updateProfileImage(byte[] image, String doctor) {
        return jdbcTemplate.update("UPDATE images SET image = ? WHERE doctor = ?", image, doctor);
    }
        */
    //Hibernate

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public long createProfileImage(byte[] image, String doctor) {
        Image im = new Image();
        im.setImage(image);
        im.setLicense(doctor);
        entityManager.persist(image);
        return im.getId();
    }

    @Override
    public Image getProfileImage(String doctor){
        TypedQuery<Image> query = entityManager.createQuery("from Image as image where image.doctor.license = :doctor",Image.class);
        query.setParameter("doctor",doctor);
        List<Image> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public long updateProfileImage(byte[] image, String doctor){
        Query query = entityManager.createQuery("update Image as im set im.image = :image where im.doctor.license = :doctor");
        query.setParameter("image",image);
        query.setParameter("doctor",doctor);
        return query.executeUpdate();
    }

}
