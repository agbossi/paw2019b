package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DoctorDaoImpl implements DoctorDao {
    private JdbcTemplate jdbcTemplate;

    private final static RowMapper<Doctor> ROW_MAPPER = new RowMapper<Doctor>() {
        @Override
        public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Doctor(resultSet.getString("nombre"), resultSet.getString("especialidad"),resultSet.getString("locacion"),resultSet.getLong("matricula"));
        }
    };

    @Autowired
    public DoctorDaoImpl(final DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public List<Doctor> getDoctorByLocation(String location) {
        final List<Doctor> list = jdbcTemplate.query("select * from doctor where locacion = ?",ROW_MAPPER,location);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
}
