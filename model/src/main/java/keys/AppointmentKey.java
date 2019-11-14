package keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class AppointmentKey implements Serializable {

    @Column
    private String doctor;

    @Column
    private String patient;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    public AppointmentKey(){

    }

    public AppointmentKey(String doctor,String clinic,Calendar date){
        this.doctor = doctor;
        this.patient = clinic;
        this.date = date;
    }

    public Calendar getDate() {
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

    public void setDate(Calendar date) {
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
