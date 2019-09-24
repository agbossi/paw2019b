package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface DoctorDao {
    public Doctor createDoctor(Specialty specialty,String license, String phoneNumber,String email);

    public List<Doctor> getDoctors();

    //List<Doctor> getDoctorByLocation(Location location);

    public List<Doctor> getDoctorByName(String fistName,String lastName);

    public List<Doctor> getDoctorBySpecialty(Specialty specialty);

    //List<Doctor> getFilteredDoctors(Location location, Specialty specialty, String clinic);

    public Doctor getDoctorByLicense(String license);

    //List<Doctor> getDoctorByClinic(Clinic clinic);

    public boolean isDoctor(String email);
}
