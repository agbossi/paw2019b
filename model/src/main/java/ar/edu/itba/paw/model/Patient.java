package ar.edu.itba.paw.model;

import java.util.HashSet;
import java.util.List;

public class Patient {

    private String firstName;

    private String lastName;

    private String email;

    private String prepaid;

    private String prepaidNumber;

    private String id;

    private List<Appointment> appointments;

    //TODO es necesario poner la otra punta de la relacion?
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "favorites",joinColumns = {@JoinColumn(name = "patientEmail")},inverseJoinColumns = {@JoinColumn(name = "doctorLicense")})
    private List<Doctor> favorites;

    public Patient(String email, String id, String prepaid, String prepaidNumber, String firstName, String lastName) {
        this.email = email;
        this.id = id;
        this.prepaid = prepaid;
        this.prepaidNumber = prepaidNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public String getPrepaidNumber() {
        return prepaidNumber;
    }


    public String getEmail() {
        return email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Doctor> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Doctor> favorites) {
        this.favorites = favorites;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Patient){
            return ((Patient) obj).getEmail().equals(this.getEmail());
        }
        return false;
    }
}
