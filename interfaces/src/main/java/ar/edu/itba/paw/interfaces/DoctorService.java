package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Specialty specialty,String license, String phoneNumber, String email);

    List<Doctor> getDoctors();

    //List<Doctor> getDoctorByLocation(Location location);

    List<Doctor> getDoctorByName(String firstName,String lastName);

    List<Doctor> getDoctorBySpecialty(Specialty specialty);

    //List<Doctor> getDoctorBy(Location location, Specialty specialty, String clinic);

    Doctor getDoctorByLicense(String license);

    //List<Doctor> getDoctorByClinic(Clinic clinic);
}
