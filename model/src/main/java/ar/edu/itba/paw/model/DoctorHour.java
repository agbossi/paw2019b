package ar.edu.itba.paw.model;

import java.util.Calendar;

public class DoctorHour {
    Calendar date;

    boolean isScheduled;

    Appointment appointment;

    public DoctorHour(Calendar date, boolean isScheduled, Appointment appointment) {
        this.date = date;
        this.isScheduled = isScheduled;
        this.appointment = appointment;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean getScheduled() {
        return isScheduled;
    }

    public Appointment getHasAppointment() {
        return appointment;
    }
}