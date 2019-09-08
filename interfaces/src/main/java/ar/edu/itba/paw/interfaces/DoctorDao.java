package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Doctor;

import java.util.List;

public interface DoctorDao {

    List<Doctor> getDoctorByLocation(String location);

    Doctor createDoctor(String name, String specialty, String location, String license);
}
