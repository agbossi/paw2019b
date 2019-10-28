package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;

import java.util.List;

public interface PatientService {
    Patient create(String id, String prepaid, String prepaidNumber, User user);

    Patient getPatientByEmail(String email);

    void setAppointments(Patient patient);

    void updatePatient(String email, String prepaid, String prepaidNumber, String id);

    List<Patient> getPatientsByPrepaid(String prepaid);
}
