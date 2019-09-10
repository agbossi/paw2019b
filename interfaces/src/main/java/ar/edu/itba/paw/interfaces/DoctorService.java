package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(String name, String specialty, String location, String license, String phoneNumber);

    List<Doctor> getDoctors();

    List<Doctor> getDoctorByLocation(String location);

    List<Doctor> getDoctorByName(String name);

    List<Doctor> getDoctorBySpecialty(String specialty);

    List<Doctor> getDoctorByClinic(String clinic);

    List<Doctor> getDoctorBy(String location,String specialty,String clinic);

    Doctor getDoctorByLicense(String license);
}
