package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditDoctorProfileForm {

    // User information

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;

    @Size(min = 6, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    private String email;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String oldPassword;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String newPassword;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String repeatNewPassword;

    // Doctor information

    @Pattern(regexp = "[a-zA-Z]+")
    private String specialty;

    @Pattern(regexp = "[0-9]+")
    private String license;

    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
