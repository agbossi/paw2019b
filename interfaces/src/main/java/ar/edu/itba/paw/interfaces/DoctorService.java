package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Specialty specialty,String license, String phoneNumber, String email);

    public List<Doctor> getDoctors();

    public List<Doctor> getDoctorByName(String firstName,String lastName);

    public List<Doctor> getDoctorBySpecialty(Specialty specialty);

    //List<Doctor> getDoctorBy(Location location, Specialty specialty, String clinic);

    public Doctor getDoctorByLicense(String license);

    public Doctor getDoctorByEmail(String email);

}
