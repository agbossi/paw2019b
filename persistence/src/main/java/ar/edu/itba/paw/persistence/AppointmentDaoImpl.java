package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Appointment createAppointment(DoctorClinic doctorClinic, User patient, Calendar date){
        Appointment appointment = new Appointment(date,doctorClinic,patient);
        entityManager.persist(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap" +
                " where ap.doctorClinic.doctor.license = :doctor and ap.doctorClinic.clinic.id = :clinic",Appointment.class);
        query.setParameter("doctor",doctorClinic.getDoctor().getLicense());
        query.setParameter("clinic",doctorClinic.getClinic().getId());
        List<Appointment> list = query.getResultList();
        return list;
    }

    @Override
    public List<Appointment> getPatientsAppointments(User patient){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap " +
                "where ap.patient.email = :email",Appointment.class);
        query.setParameter("email",patient.getEmail());
        List<Appointment> list = query.getResultList();
        return list;
    }

    @Override //TODO check extract thing
    public List<Appointment> getAllDocAppointmentsOnSchedule(DoctorClinic doctor, int day, int hour){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap" +
                " where ap.doctorClinic.doctor.license = :doctor and ap.clinic = :clinic and " +
                "DAY(ap.appointmentKey.date) = :day and HOUR(ap.appointmentKey.date) = :hour ",Appointment.class);
        query.setParameter("doctor",doctor.getDoctor().getLicense());
        query.setParameter("clinic",doctor.getClinic().getId());
        query.setParameter("day",day-1);
        query.setParameter("hour",hour);
        List<Appointment> list = query.getResultList();
        return list;

    }
    @Transactional
    @Override
    public void cancelAppointment(DoctorClinic doctorClinic, User patient, Calendar date){
        final Query query = entityManager.createQuery(
                "delete from Appointment as ap where ap.appointmentKey.patient = :email and " +
                        "ap.appointmentKey.doctor = :doctor and ap.clinic = :clinic " +
                        "and ap.appointmentKey.date = :date");
        query.setParameter("email",patient.getEmail());
        query.setParameter("doctor",doctorClinic.getDoctor().getLicense());
        query.setParameter("clinic",doctorClinic.getClinic().getId());
        query.setParameter("date",date);
        query.executeUpdate();
    }

    @Override
    public Appointment hasAppointment(DoctorClinic doctorClinic, Calendar date){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap" +
                " where ap.doctorClinic.doctor.license = :doctor and ap.clinic = :clinic " +
                "and ap.appointmentKey.date = :date",Appointment.class);
        query.setParameter("doctor",doctorClinic.getDoctor().getLicense());
        query.setParameter("clinic",doctorClinic.getClinic().getId());
        query.setParameter("date",date);
        List<Appointment> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Appointment> getAllDoctorsAppointments(Doctor doctor){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap" +
                " where ap.doctorClinic.doctor.license = :doctor",Appointment.class);
        query.setParameter("doctor",doctor.getLicense());
        List<Appointment> list = query.getResultList();
        return list;
    }

    @Override
    public List<Appointment> getDoctorAppointmentsWithinWeek(Doctor doctor, Calendar weekBeginning, Calendar weekEnd){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap" +
                " where ap.doctorClinic.doctor.license = :doctor and" +
                " ap.appointmentKey.date between :startDate and :endDate" +
                " order by ap.appointmentKey.date",Appointment.class);
        query.setParameter("doctor",doctor.getLicense())
                .setParameter("startDate", weekBeginning)
                .setParameter("endDate", weekEnd);
        List<Appointment> list = query.getResultList();
        return list;
    }

    @Override
    public boolean hasAppointment(String doctorLicense, String patientEmail, Calendar date){
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment as ap" +
                " where ap.doctorClinic.doctor.license = :doctor and ap.patient.email = :email " +
                "and ap.appointmentKey.date = :date",Appointment.class);
        query.setParameter("doctor",doctorLicense);
        query.setParameter("email",patientEmail);
        query.setParameter("date",date);
        List<Appointment> list = query.getResultList();
        return !list.isEmpty();
    }

    //TODO finish how to do the date parts
    @Override
    public void cancelAllAppointmentsOnSchedule(DoctorClinic doctorClinic, int day, int hour){
        final Query query = entityManager.createQuery("delete from Appointment as ap where " +
                "ap.appointmentKey.doctor = :doctor and ap.clinic = :clinic and " +
                "DAY(ap.appointmentKey.date) = :day and HOUR(ap.appointmentKey.date) = :hour");
        query.setParameter("doctor",doctorClinic.getDoctor().getLicense());
        query.setParameter("clinic",doctorClinic.getClinic().getId());
        query.setParameter("day",day-1);
        query.setParameter("hour",hour);
        query.executeUpdate();
    }


}
