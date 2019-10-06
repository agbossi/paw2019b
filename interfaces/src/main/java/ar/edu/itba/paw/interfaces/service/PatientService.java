package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;

public interface PatientService {
    Patient create(String email, String id, String prepaid, String prepaidNumber, User user);

    Patient getPatientByEmail(String email);

    void setAppointments(Patient patient);
}
