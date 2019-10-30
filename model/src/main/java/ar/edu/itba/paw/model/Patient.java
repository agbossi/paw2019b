package ar.edu.itba.paw.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    //TODO ver que es el param cascade de la anotation

    @OneToOne
    @JoinColumn(name = "email")
    @MapsId
    private User user;

    //TODO poner los nullable = false donde corresponda

    @Column(name = "prepaid")
    private String prepaid;

    @Column(name = "prepaidNumber")
    private String prepaidNumber;

    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient(String id, String prepaid, String prepaidNumber,User user) {
        this.user = user;
        this.id = id;
        this.prepaid = prepaid;
        this.prepaidNumber = prepaidNumber;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Patient){
            return ((Patient) obj).getEmail().equals(this.getEmail());
        }
        return false;
    }
}
