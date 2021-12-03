package ar.edu.itba.paw.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AppointmentKey implements Serializable {

    @Column
    private String doctor;

    @Column
    private String patient;

    @Column
    private LocalDateTime date;

    // DO NOT DELETE
    public AppointmentKey(){

    }

    public AppointmentKey(String doctor,String clinic, LocalDateTime date){
        this.doctor = doctor;
        this.patient = clinic;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setDoctorLicense(String doctorLicense) {
        this.doctor = doctorLicense;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentKey)) return false;
        AppointmentKey k = (AppointmentKey) o;
        return Objects.equals(getDoctor(), k.getDoctor()) &&
                Objects.equals(getPatient(), k.getPatient()) &&
                Objects.equals(getDate(),k.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDoctor(), getPatient());
    }
}
