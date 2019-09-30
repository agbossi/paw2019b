package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.AppointmentDao;
import ar.edu.itba.paw.interfaces.AppointmentService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentDao appointmentDao;

    @Override
    public Appointment createAppointment(DoctorClinic doctorClinic, Patient patient, Calendar date) {
        return appointmentDao.createAppointment(doctorClinic,patient,date);
    }

    @Override
    public List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic) {
        return appointmentDao.getDoctorsAppointments(doctorClinic);
    }

    @Override
    public List<Appointment> getPatientsAppointments(Patient patient) {
        return appointmentDao.getPatientsAppointments(patient);
    }
}
