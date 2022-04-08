package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Doctor;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class DoctorDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String specialty;
    private String license;
    private String phoneNumber;
    private URI profileImage;

    public static DoctorDto fromDoctor(Doctor doctor, UriInfo uriInfo) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.license = doctor.getLicense();
        doctorDto.specialty = doctor.getSpecialty().getSpecialtyName();
        doctorDto.phoneNumber = doctor.getPhoneNumber();
        doctorDto.firstName = doctor.getUser().getFirstName();
        doctorDto.lastName = doctor.getUser().getLastName();
        doctorDto.email = doctor.getUser().getEmail();
        doctorDto.profileImage = uriInfo.getBaseUriBuilder().path("doctors").path(doctor.getLicense()).path("ProfileImage").build();
        return doctorDto;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public URI getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(URI profileImage) {
        this.profileImage = profileImage;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
