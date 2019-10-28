package ar.edu.itba.paw.model;

import keys.AppointmentKey;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    //poner lo que va en bd en la clase key esto va como transient o no se rompe por estar en la key?
    private Calendar date;

    //no estoy nada seguro de los name
    //la key con doctor clinic choca?
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "doctor",
                    referencedColumnName = "doctor"),
            @JoinColumn(
                    name = "clinic",
                    referencedColumnName = "clinic")
    })
    private DoctorClinic doctorClinic;

    //tengo que inicializar esto en el constructor y poner datos aca?
    @EmbeddedId
    private AppointmentKey appointmentKey;

    @ManyToOne
    @JoinColumn(name = "patient")
    private User patient;

    public Appointment(Calendar date, DoctorClinic doctorClinic, User patient) {
        this.date = date;
        this.doctorClinic = doctorClinic;
        this.patient = patient;
    }
    public Appointment(){
    }

    public Calendar getDate() {
        return date;
    }

    public DoctorClinic getDoctorClinic() {
        return doctorClinic;
    }

    public User getPatient() {
        return patient;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDoctorClinic(DoctorClinic doctorClinic) {
        this.doctorClinic = doctorClinic;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }
}
