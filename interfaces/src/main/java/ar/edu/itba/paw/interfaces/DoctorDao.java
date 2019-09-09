package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Doctor;

import java.util.List;

public interface DoctorDao {

    List<Doctor> getDoctors();

    List<Doctor> getDoctorByLocation(String location);

    List<Doctor> getDoctorByName(String name);

    List<Doctor> getDoctorBySpecialty(String specialty);

    List<Doctor> getDoctorByClinic(String clinic);

    Doctor getDoctorByLicense(String license);

    Doctor createDoctor(String name, String specialty, String location, String license);
}
