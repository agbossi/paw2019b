package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;

import java.util.Map;

public interface PatientDao {
    Patient create(String id, String prepaid, String prepaidNumber, User user);

    Patient getPatientByEmail(String email);

    void updatePatient(String email, Map<String, String> args);
}
