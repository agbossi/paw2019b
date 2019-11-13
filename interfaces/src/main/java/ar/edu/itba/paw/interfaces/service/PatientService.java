package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;

public interface PatientService {
    Patient create(String id, String prepaid, String prepaidNumber, String firstName, String lastName, String password, String email);

    Patient getPatientByEmail(String email);

    void setAppointments(Patient patient);

    void updatePatient(String email, String prepaid, String prepaidNumber, String id);
}
