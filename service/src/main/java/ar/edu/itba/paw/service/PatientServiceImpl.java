package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.dao.PatientDao;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Patient create(String email,String id, String prepaid, String prepaidNumber, User user) {
        return patientDao.create(email,id, prepaid, prepaidNumber, user);
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return patientDao.getPatientByEmail(email);
    }

    @Override
    public void setAppointments(Patient patient) {
        User user = userDao.findUserByEmail(patient.getEmail());
        List<Appointment> appointments = appointmentDao.getPatientsAppointments(user);
        patient.setAppointments(appointments);
    }

}
