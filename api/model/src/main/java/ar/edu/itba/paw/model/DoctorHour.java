package ar.edu.itba.paw.model;

import java.time.LocalDateTime;

public class DoctorHour {

    private LocalDateTime date;

    private boolean isScheduled;

    private boolean isClinic;

    private Appointment appointment;

    public DoctorHour(LocalDateTime date, boolean isScheduled, boolean isClinic, Appointment appointment) {
        this.date = date;
        this.isScheduled = isScheduled;
        this.appointment = appointment;
        this.isClinic = isClinic;
    }

    public LocalDateTime getDate() {
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
