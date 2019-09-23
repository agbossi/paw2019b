package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Patient;

public interface PatientService {
    Patient create(String id, String prepaid, String prepaidNumber);

    Patient getPatientById(String id);
}
