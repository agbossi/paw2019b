package ar.edu.itba.paw.model;

import java.util.Calendar;
import java.util.Date;

public class Appointment {

    private Calendar date;

    DoctorClinic doctorClinic;

    Patient patient;

    public Appointment(Calendar date, DoctorClinic doctorClinic, Patient patient) {
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

    public Patient getPatient() {
        return patient;
    }
}
