package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Map;

public interface PatientDao {
    Patient create(String email, String id, String prepaid, String prepaidNumber, User user);

    Patient getPatientByEmail(String email);

    void updatePatient(String email, Map<String, String> args);

    List<Patient> getPatientsByPrepaid(String prepaid);

    void addDoctorToFavorites(Patient patient, Doctor doctor);

    List<Doctor> getPatientFavoriteDoctors(Patient patient);
}
