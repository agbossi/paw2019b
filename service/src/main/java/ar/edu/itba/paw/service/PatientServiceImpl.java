package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.AppointmentDao;
import ar.edu.itba.paw.interfaces.PatientDao;
import ar.edu.itba.paw.interfaces.PatientService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public Patient create(String email,String id, String prepaid, String prepaidNumber) {
        return patientDao.create(email,id, prepaid, prepaidNumber);
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return patientDao.getPatientByEmail(email);
    }

    @Override
    public void setAppointments(Patient patient) {
        List<Appointment> appointments = appointmentDao.getPatientsAppointments(patient);
        patient.setAppointments(appointments);
    }

}
