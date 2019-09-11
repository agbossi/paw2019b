package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(String name, Specialty specialty, Location location, String license, String phoneNumber);

    List<Doctor> getDoctors();

    List<Doctor> getDoctorByLocation(Location location);

    List<Doctor> getDoctorByName(String name);

    List<Doctor> getDoctorBySpecialty(Specialty specialty);

    List<Doctor> getDoctorBy(Location location, Specialty specialty, String clinic);

    Doctor getDoctorByLicense(String license);
}
