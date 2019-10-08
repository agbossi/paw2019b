package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.ScheduleDao;
import ar.edu.itba.paw.model.Doctor;
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
            return new Schedule(resultSet.getInt("day"), resultSet.getInt("hour"));
        }
    };
    @Autowired
    public ScheduleDaoImpl(final DataSource ds){

        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedule");

    }

    @Override
    public Schedule createSchedule(int day, int hour, DoctorClinic doctorClinic) {
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
        final List<Schedule> list = jdbcTemplate.query( "select * from schedule where doctor = ? and clinic =?",
                                                       ROW_MAPPER, doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId());
        return (list.isEmpty() ? null : list );
    }

    @Override
    public boolean doctorHasScheduleInClinic(DoctorClinic doctorClinic, int day, int hour) {
        final List<Schedule> list = jdbcTemplate.query( "select * from schedule where doctor = ? and clinic =? and day = ? and hour = ?", ROW_MAPPER, doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId(), day, hour);

        return (list.isEmpty() ? false : true );
    }
    @Override
    public boolean doctorHasSchedule(Doctor doctor, int day, int hour) {
        final List<Schedule> list = jdbcTemplate.query( "select * from schedule where doctor = ? and day = ? and hour = ?", ROW_MAPPER, doctor.getLicense(),day, hour);

        return (list.isEmpty() ? false : true );
    }

    @Override
    public void deleteSchedule(int hour, int day, DoctorClinic doctorClinic) {
        Object[] args = new Object[] {hour, day, doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId()};
        jdbcTemplate.update("delete from schedule where hour = ? and day = ? and doctor = ? and clinic = ?", args);
    }
}
