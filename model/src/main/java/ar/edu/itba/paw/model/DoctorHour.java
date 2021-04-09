package ar.edu.itba.paw.model;

import java.util.Calendar;

public class DoctorHour {

    private Calendar date;

    private boolean isScheduled;

    private boolean isClinic;

    private Appointment appointment;

    public DoctorHour(Calendar date, boolean isScheduled, boolean isClinic, Appointment appointment) {
        this.date = date;
        this.isScheduled = isScheduled;
        this.appointment = appointment;
        this.isClinic = isClinic;
    }

    public Calendar getDate() {
        return date;
    }

    public boolean getScheduled() {
        return isScheduled;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public boolean hasAppointment() { return appointment != null; }

    public boolean isClinic() {
        return isClinic;
    }
}
