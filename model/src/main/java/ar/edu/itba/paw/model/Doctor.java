package ar.edu.itba.paw.model;


public class Doctor {

    private String name;

    private String specialty;

    private String location;

    private String license;

    private String phoneNumber;

    public Doctor(String name, String specialty, String location, String license, String phoneNumber){
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.license = license;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getLocation() {
        return location;
    }

    public String getLicense() {
        return license;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
