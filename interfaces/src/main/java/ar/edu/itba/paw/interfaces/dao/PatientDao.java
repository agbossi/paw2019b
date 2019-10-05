package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Patient;

public interface PatientDao {
    Patient create(String email, String id, String prepaid, String prepaidNumber);

    Patient getPatientByEmail(String email);


}
