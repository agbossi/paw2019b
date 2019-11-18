package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;

import java.util.List;

public interface DoctorService extends PaginationService<Doctor> {
    Doctor createDoctor(Specialty specialty, String license, String phoneNumber, User user);

    List<Doctor> getDoctors();

    List<Doctor> getDoctorByName(String firstName,String lastName);

    List<Doctor> getDoctorBySpecialty(Specialty specialty);

    Doctor getDoctorByLicense(String license);

    Doctor getDoctorByEmail(String email);

    List<Doctor> getDoctorsWithAvailability();

    boolean isDoctor(String email);

    long deleteDoctor(String license);

    void updateDoctor(String license, String phoneNumber, String specialty);

    List<String> getAvailableFilteredLicenses(Location location, Specialty specialty,
                                                      String firstName, String lastName, Prepaid prepaid, int consultPrice);

    List<String> getAvailableDoctorsLicenses();

    List<Doctor> getPaginatedDoctors(List<String> licenses, int page);

    int getMaxAvailableDoctorsPage(List<String> licenses);
}
