package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;

import java.util.List;
import java.util.Map;

public interface DoctorDao {
    Doctor createDoctor(Specialty specialty,String license, String phoneNumber,String email);

    List<Doctor> getDoctors();

    List<Doctor> getDoctorByName(String fistName,String lastName);

    List<Doctor> getDoctorBySpecialty(Specialty specialty);

    Doctor getDoctorByLicense(String license);

    boolean isDoctor(String email);

    Doctor getDoctorByEmail(String email);

    long deleteDoctor(String license);

    void updateDoctor(String license, Map<String, String> args);
}
