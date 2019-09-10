package ar.edu.itba.paw.model;


public class Doctor {
    private String name;
    private String specialty;
    private String location;
    private String license;
    private Clinic clinic;

    public Doctor(){
    }
    public Doctor(String name, String specialty, String location, String license){
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.license = license;
    }

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
}
