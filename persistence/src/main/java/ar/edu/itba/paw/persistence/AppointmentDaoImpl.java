package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.AppointmentDao;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Appointment> ROW_MAPPER = new RowMapper<Appointment>() {
        @Override
        public Appointment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Appointment(DateHelper.dateToCalendar(resultSet.getTimestamp("date")),
                    new DoctorClinic(new Doctor(resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            new Specialty(resultSet.getString("specialty")),
                            resultSet.getString("doctorLicense"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getString("email")),
                            new Clinic(resultSet.getInt("clinicid"),resultSet.getString("name"),
                                    new Location(resultSet.getString("location"))),
                            resultSet.getInt("consultPrice")),
                    new Patient(resultSet.getString("email"),
                            resultSet.getString("prepaid"),
                            resultSet.getString("prepaidNumber")));
        }
    };

    @Autowired
    public AppointmentDaoImpl(DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("appointments");
    }

    @Override
    public Appointment createAppointment(DoctorClinic doctorClinic, Patient patient,Calendar date) {
        final Map<String, Object> args = new HashMap<>();
        args.put("doctor", doctorClinic.getDoctor().getLicense());
        args.put("clinic", doctorClinic.getClinic().getId());
        args.put("date", date.getTime());
        args.put("patient", patient.getId());
        jdbcInsert.execute(args);

        return new Appointment(date, doctorClinic, patient);
    }

    public List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic){
        final List<Appointment> list = jdbcTemplate.query("select * from (appointments join (((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) " +
                        "join clinics on doctorclinics.clinicid = clinics.id)" +
                        "join users on doctors.email = users.email)" +
                        "on (doctors.license = appointments.doctor and doctorclinics.clinicid = appointments.clinic) join patients on appointments.patient = patients.email" +
                        ") where appointments.doctor = ? and appointments.clinic = ?",ROW_MAPPER,
                doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId());
        if(list.isEmpty()){
            return null;
        }
        return list;

    }

    @Override
    public List<Appointment> getPatientsAppointments(Patient patient) {
        final List<Appointment> list = jdbcTemplate.query("select * from (appointments join (((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) " +
                "join clinics on doctorclinics.clinicid = clinics.id)" +
                "join users on doctors.email = users.email)" +
                "on (doctors.license = appointments.doctor and doctorclinics.clinicid = appointments.clinic) join patients on appointments.patient = patients.email" +
                ") where appointments.patient = ?",ROW_MAPPER,patient.getId());
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
}
