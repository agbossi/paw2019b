package ar.edu.itba.paw.model;

<<<<<<< HEAD

import javax.persistence.*;

import java.util.HashSet;

=======
import java.util.HashSet;
>>>>>>> b582cab152f5cb63a19474d4cbaa6bfd0f2505e8
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    //TODO ver que es el param cascade de la anotation

    @OneToOne
    @JoinColumn(name = "email")
    @MapsId
    private User user;

    @Id
    private String email;

    //TODO poner los nullable = false donde corresponda

    @Column(name = "prepaid")
    private String prepaid;

    @Column(name = "prepaidNumber")
    private String prepaidNumber;

    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    //TODO es necesario poner la otra punta de la relacion?
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "favorites",joinColumns = {@JoinColumn(name = "patientEmail")},inverseJoinColumns = {@JoinColumn(name = "doctorLicense")})
    private List<Doctor> favorites;
<<<<<<< HEAD
    
    public Patient(String id, String prepaid, String prepaidNumber,User user) {
        this.user = user;
=======

    public Patient(String email, String id, String prepaid, String prepaidNumber, String firstName, String lastName) {
        this.email = email;
>>>>>>> b582cab152f5cb63a19474d4cbaa6bfd0f2505e8
        this.id = id;
        this.prepaid = prepaid;
        this.prepaidNumber = prepaidNumber;
        this.email = user.getEmail();
    }

    public Patient(){
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
        return user.getEmail();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public void setFirstName(String firstName) {
        this.user.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.user.setLastName(lastName);
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public void setPrepaidNumber(String prepaidNumber) {
        this.prepaidNumber = prepaidNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Doctor> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Doctor> favorites) {
        this.favorites = favorites;
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
