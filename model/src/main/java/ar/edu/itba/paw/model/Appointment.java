package ar.edu.itba.paw.model;

import java.util.Calendar;
import java.util.Date;

public class Appointment {

    private Calendar date;

    DoctorClinic doctorClinic;

    User patient;

    public Appointment(Calendar date, DoctorClinic doctorClinic, User patient) {
        this.date = date;
        this.doctorClinic = doctorClinic;
        this.patient = patient;
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
}
