package ar.edu.itba.paw.model;


public class Doctor {

    private String name;

    private Specialty specialty;

    private Location location;

    private String license;

    private String phoneNumber;

    private Clinic clinic;

    public Doctor(String name, Specialty specialty, Location location, String license, String phoneNumber, Clinic clinic){
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.license = license;
        this.phoneNumber = phoneNumber;
        this.clinic = clinic;
    }

    public String getName() {
        return name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public Location getLocation() {
        return location;
    }

    public String getLicense() {
        return license;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Clinic getClinic() {
        return clinic;
    }
}
