package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;

public interface PatientDao {
    Patient create(String email, String id, String prepaid, String prepaidNumber, User user);

    Patient getPatientByEmail(String email);


}
