package ar.edu.itba.paw.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "appointments")
public class Appointment {

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "doctor",insertable = false, updatable = false,
                    referencedColumnName = "doctorLicense"),
            @JoinColumn(
                    name = "clinic",insertable = false, updatable = false,
                    referencedColumnName = "clinicid")
    })
    private DoctorClinic doctorClinic;

    @Column
    private int clinic;

    @EmbeddedId
    private AppointmentKey appointmentKey;

    @ManyToOne
    @JoinColumn(name = "patient", insertable = false, updatable = false)
    private User patient;

    public Appointment(Calendar date, DoctorClinic doctorClinic, User patient) {
        this.clinic = doctorClinic.getClinic().getId();
        this.doctorClinic = doctorClinic;
        this.patient = patient;
        this.appointmentKey = new AppointmentKey(doctorClinic.getDoctor().getLicense(),patient.getEmail(),date);
    }

    public Appointment(){
    }

    public DoctorClinic getDoctorClinic() {
        return doctorClinic;
    }

    public User getPatient() {
        return patient;
    }


    public void setDoctorClinic(DoctorClinic doctorClinic) {
        this.doctorClinic = doctorClinic;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public AppointmentKey getAppointmentKey() {
        return appointmentKey;
    }
}
