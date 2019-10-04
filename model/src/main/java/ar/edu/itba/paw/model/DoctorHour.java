package ar.edu.itba.paw.model;

import java.util.Calendar;

public class DoctorHour {
    Calendar date;

    boolean isScheduled;

    boolean hasAppointment;

    public DoctorHour(Calendar date, boolean isScheduled, boolean hasAppointment) {
        this.date = date;
        this.isScheduled = isScheduled;
        this.hasAppointment = hasAppointment;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean getScheduled() {
        return isScheduled;
    }

    public boolean getHasAppointment() {
        return hasAppointment;
    }
}
