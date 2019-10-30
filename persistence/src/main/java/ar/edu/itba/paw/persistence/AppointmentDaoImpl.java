package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.model.*;
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
import java.util.*;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

    private JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Appointment> ROW_MAPPER = new RowMapper<Appointment>() {
        @Override
        public Appointment mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Appointment(DateHelper.dateToCalendar(resultSet.getTimestamp("date")),
                    new DoctorClinic(new Doctor(resultSet.getString("docFname"),
                            resultSet.getString("docLname"),
                            new Specialty(resultSet.getString("specialty")),
                            resultSet.getString("doctorLicense"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getString("docEmail")),
                            new Clinic(resultSet.getInt("clinicid"),
                                       resultSet.getString("name"),
                                       resultSet.getString("address"),
                                       new Location(resultSet.getString("location"))),
                            resultSet.getInt("consultPrice")),
                    new User(resultSet.getString("patFname"),
                            resultSet.getString("patLname"),
                            null,
                            resultSet.getString("patient")
                            ));
        }
    };

    @Autowired
    public AppointmentDaoImpl(DataSource ds){
        jdbcTemplate = new JdbcTemplate(ds);

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("appointments");
    }

    public Appointment createAppointment(DoctorClinic doctorClinic, User patient, Calendar date) {
        final Map<String, Object> args = new HashMap<>();
        args.put("doctor", doctorClinic.getDoctor().getLicense());
        args.put("clinic", doctorClinic.getClinic().getId());
        args.put("date", date.getTime());
        args.put("patient", patient.getEmail());
        jdbcInsert.execute(args);

        return new Appointment(date, doctorClinic, patient);
    }

    @Override
    public List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic){
        final List<Appointment> list = jdbcTemplate.query("select date, docli.firstName as docFname, docli.lastName as docLname, docli.specialty as specialty, doctorLicense, phoneNumber, docli.email as docEmail, " +
                        "clinicid, name,address, location, consultPrice, patient, pat.firstName as patFname, pat.lastName as patLname  " +
                        " from (appointments join users as pat on pat.email = appointments.patient) " +
                        "join (((doctorclinics natural join doctors) join clinics on doctorclinics.clinicid = clinics.id) " +
                        "natural join users) as docli on (docli.license = appointments.doctor and docli.clinicid = appointments.clinic) " +
                        "where appointments.doctor = ? and appointments.clinic = ?",ROW_MAPPER,
                doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId());
        if(list.isEmpty()){
            return null;
        }
        return list;

    }

    @Override
    public List<Appointment> getPatientsAppointments(User patient) {
        final List<Appointment> list = jdbcTemplate.query("select date, docli.firstName as docFname, docli.lastName as docLname, docli.specialty as specialty, doctorLicense, phoneNumber, docli.email as docEmail, " +
                "clinicid, name,address, location, consultPrice, patient, pat.firstName as patFname, pat.lastName as patLname  " +
                " from (appointments join users as pat on pat.email = appointments.patient) " +
                "join (((doctorclinics join doctors on doctorclinics.doctorLicense = doctors.license) join clinics on doctorclinics.clinicid = clinics.id) " +
                "natural join users) as docli on (docli.license = appointments.doctor and docli.clinicid = appointments.clinic) " +
                "where appointments.patient = ?",ROW_MAPPER,patient.getEmail());
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public void cancelAppointment(DoctorClinic doctorClinic, User patient, Calendar date){
        Object[] args = new Object[] {patient.getEmail(), doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId(), date.getTime()};
        jdbcTemplate.update("delete from appointments where patient = ? and doctor = ? and clinic = ? and date = ?", args);

    }

    @Override
    public Appointment hasAppointment(DoctorClinic doctorClinic, Calendar date) {
        final List<Appointment> list = jdbcTemplate.query("" +
                        "select date, docli.firstName as docFname, docli.lastName as docLname, docli.specialty as specialty, doctorLicense, phoneNumber, docli.email as docEmail, " +
                        "clinicid, name,address, location, consultPrice, patient, pat.firstName as patFname, pat.lastName as patLname  " +
                        " from (appointments join users as pat on pat.email = appointments.patient) " +
                        "join (((doctorclinics natural join doctors) join clinics on doctorclinics.clinicid = clinics.id) " +
                        "natural join users) as docli on (docli.license = appointments.doctor and docli.clinicid = appointments.clinic) " +
                        "where appointments.doctor = ? and appointments.clinic = ? and date = ?",ROW_MAPPER,
                            doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId(), date.getTime());
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Appointment> getAllDoctorsAppointments(Doctor doctor) {
        final List<Appointment> list = jdbcTemplate.query("" +
                        "select date, docli.firstName as docFname, docli.lastName as docLname, docli.specialty as specialty, doctorLicense, phoneNumber, docli.email as docEmail, " +
                        "clinicid, name,address, location, consultPrice, patient, pat.firstName as patFname, pat.lastName as patLname  " +
                        " from (appointments join users as pat on pat.email = appointments.patient) " +
                        "join (((doctorclinics natural join doctors) join clinics on doctorclinics.clinicid = clinics.id) " +
                        "natural join users) as docli on (docli.license = appointments.doctor and docli.clinicid = appointments.clinic) " +
                        "where appointments.doctor = ?",ROW_MAPPER,
                doctor.getLicense());
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public boolean hasAppointment(String doctorLicense, String patientEmail, Calendar date) {
        final List<Appointment> list = jdbcTemplate.query("" +
                        "select date, docli.firstName as docFname, docli.lastName as docLname, docli.specialty as specialty, doctorLicense, phoneNumber, docli.email as docEmail, " +
                        "clinicid, name,address, location, consultPrice, patient, pat.firstName as patFname, pat.lastName as patLname  " +
                        " from (appointments join users as pat on pat.email = appointments.patient) " +
                        "join (((doctorclinics natural join doctors) join clinics on doctorclinics.clinicid = clinics.id) " +
                        "natural join users) as docli on (docli.license = appointments.doctor and docli.clinicid = appointments.clinic) " +
                        "where appointments.doctor = ? and date = ? and pat.email = ?",ROW_MAPPER,
                doctorLicense, date.getTime(),patientEmail);
        return !list.isEmpty();
    }

    @Override
    public List<Appointment> getAllDocAppointmentsOnSchedule(DoctorClinic doctor, int day, int hour){
        final List<Appointment> list = jdbcTemplate.query("" +
                        "select date, docli.firstName as docFname, docli.lastName as docLname, docli.specialty as specialty, doctorLicense, phoneNumber, docli.email as docEmail, " +
                        "clinicid, name,address, location, consultPrice, patient, pat.firstName as patFname, pat.lastName as patLname  " +
                        " from (appointments join users as pat on pat.email = appointments.patient) " +
                        "join (((doctorclinics natural join doctors) join clinics on doctorclinics.clinicid = clinics.id) " +
                        "natural join users) as docli on (docli.license = appointments.doctor and docli.clinicid = appointments.clinic) " +
                        "where appointments.doctor = ?and appointments.clinic = ? and extract(dow from date) = ? and  extract(hour from date) = ?",ROW_MAPPER,
                        doctor.getDoctor().getLicense(), doctor.getClinic().getId(), day - 1, hour);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public void cancelAllAppointmentsOnSchedule(DoctorClinic doctorClinic, int day, int hour){
        Object[] args = new Object[] {doctorClinic.getDoctor().getLicense(), doctorClinic.getClinic().getId(), day -1, hour};
        jdbcTemplate.update("delete from appointments where doctor = ? and clinic = ? and extract(dow from date) = ? and  extract(hour from date) = ?", args);

    }

    //Hibernate

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Appointment createAppointment(DoctorClinic doctorClinic, User patient, Calendar date){
        Appointment appointment = new Appointment(date,doctorClinic,patient);
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic){

    }


}
