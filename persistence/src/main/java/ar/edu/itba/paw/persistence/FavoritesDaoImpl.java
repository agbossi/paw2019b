package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.FavoritesDao;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Favorite;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FavoritesDaoImpl implements FavoritesDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Favorite> ROW_MAPPER = new RowMapper<Favorite>() {
        @Override
        public Favorite mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Favorite(new Doctor(resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    new Specialty(resultSet.getString("specialty")),
                    resultSet.getString("license"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email")));
        }
    };

    @Autowired
    public FavoritesDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("favorites");
    }

    @Override
    public void createFavorite(Patient patient, Doctor doctor) {

    }

    @Override
    public List<Doctor> getPatientFavorites(Patient patient) {
        return null;
    }
}
