package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DoctorForm {

    @Size(min = 1, max = 60)
    private String name;

    @Size(min = 1, max = 50)
    private String specialty;

    @Size(min = 2, max = 50)
    private String location;

    @Size(min = 3, max = 20)
    @Pattern(regexp = "[A-Z]+[0-9]+")
    private String license;

    @Pattern( regexp = "(5411|11)[0-9]{8}")
    private String phoneNumber;

    private String clinic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}
