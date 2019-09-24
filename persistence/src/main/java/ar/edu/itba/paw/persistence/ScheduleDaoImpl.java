package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ScheduleDao;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Schedule;
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
public class ScheduleDaoImpl implements ScheduleDao {
    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Schedule> ROW_MAPPER = new RowMapper<Schedule>() {
        @Override
        public Schedule mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Schedule(resultSet.getString("hour"), resultSet.getString("day"));
        }
    };
    @Autowired
    public ScheduleDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedule");

    }

    @Override
    public Schedule createSchedule(String day, String hour, DoctorClinic doctorClinic) {
        final Map<String, Object> args = new HashMap<>();
        args.put("day", day);
        args.put("hour", hour);
        args.put("doctor", doctorClinic.getDoctor().getLicense());
        args.put("clinic", doctorClinic.getClinic().getId());
        int result;

        result = jdbcInsert.execute(args);
        return new Schedule(day, hour);
    }

    @Override
    public List<Schedule> getDoctorClinicSchedule(DoctorClinic doctorClinic) {
        return jdbcTemplate.query( "select * from schedule where doctor = ? and clinic =?", ROW_MAPPER, doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId());
    }
}
