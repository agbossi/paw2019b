package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditDoctorProfileForm {

    // User information

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String firstName;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String lastName;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z0-9 ]*")
    private String newPassword;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z0-9 ]*")
    private String repeatPassword;

    // Doctor information

    private String specialty;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatNewPassword) {
        this.repeatPassword = repeatNewPassword;
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
