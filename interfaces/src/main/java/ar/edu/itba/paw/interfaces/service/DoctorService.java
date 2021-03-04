package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService extends PaginationService<Doctor> {
    Doctor createDoctor(Specialty specialty, String license, String phoneNumber, String firstName, String lastName, String password, String email);

    List<Doctor> getDoctors();

    List<Doctor> getDoctorByName(String firstName,String lastName);

    List<Doctor> getDoctorBySpecialty(Specialty specialty);

    Doctor getDoctorByLicense(String license);

    Doctor getDoctorByEmail(String email);

    List<Doctor> getDoctorsWithAvailability();

    boolean isDoctor(String email);

    long deleteDoctor(String license);

    void updateDoctor(String license, String phoneNumber, String specialty);

    void updateDoctorProfile(
            String email, String newPassword, String firstName, String lastName,
            String phoneNumber, String specialty,
            MultipartFile file);

    List<String> getFilteredLicenses(
            Location location, Specialty specialty,
            String firstName, String lastName, Prepaid prepaid, int consultPrice, boolean includeUnavailable);

    List<String> getAvailableDoctorsLicenses();

    List<Doctor> getPaginatedDoctors(List<String> licenses, int page);

    int getMaxAvailableDoctorsPage(List<String> licenses);
}
