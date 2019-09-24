package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DoctorForm {

    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;

    @Pattern(regexp = "[a-zA-Z]+")
    private String specialty;

    @Pattern(regexp = "[0-9]+")
    private String license;

    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    @Size(min = 8, max = 20)
    //@Pattern(regexp = "[[a-zA-Z]+[0-9]{4,}]")
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String password;

    @Size(min = 8, max = 20)
    //@Pattern(regexp = "[[a-zA-Z]+[0-9]{4,}]")
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String repeatPassword;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
