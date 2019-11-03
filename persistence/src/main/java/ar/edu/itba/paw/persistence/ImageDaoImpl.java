package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.ImageDao;
import ar.edu.itba.paw.model.Image;
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
public class ImageDaoImpl implements ImageDao {

    private JdbcTemplate jdbcTemplate;
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

}
