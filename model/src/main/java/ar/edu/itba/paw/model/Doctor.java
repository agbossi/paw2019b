package ar.edu.itba.paw.model;


public class Doctor {

    private String name;

    private Specialty specialty;

    private String license;

    private String phoneNumber;


    public Doctor(String name, Specialty specialty, String license, String phoneNumber){
        this.name = name;
        this.specialty = specialty;
        this.license = license;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }


    public String getLicense() {
        return license;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
