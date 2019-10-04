package ar.edu.itba.paw.model;

import java.util.List;

public class Patient {
    private String email;

    private String prepaid;

    private String prepaidNumber;

    private String id;

    List<Appointment> appointments;

    public Patient(String email, String id, String prepaid, String prepaidNumber) {
        this.email = email;
        this.id = id;
        this.prepaid = prepaid;
        this.prepaidNumber = prepaidNumber;
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
}
