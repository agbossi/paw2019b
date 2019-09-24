package ar.edu.itba.paw.model;


public class Doctor {

    private String firstName;

    private String lastName;

    private Specialty specialty;

    private String license;

    private String phoneNumber;

    private String email;

    public Doctor(String firstName,String lastName ,Specialty specialty,String license, String phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.license = license;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public String getEmail() {return email;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
