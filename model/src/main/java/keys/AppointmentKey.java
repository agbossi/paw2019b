package keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class AppointmentKey implements Serializable {

    @Column(name = "doctor")
    private String doctorLicense;

    @Column(name = "clinic")
    private int clinicId;

    @Column(name = "date")
    private Date date;

    public AppointmentKey(){

    }

    public AppointmentKey(String doctor,int clinic,Date date){
        this.doctorLicense = doctor;
        this.clinicId = clinic;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDoctor() {
        return doctorLicense;
    }

    public int getClinic() {
        return clinicId;
    }

    public void setDoctorLicense(String doctorLicense) {
        this.doctorLicense = doctorLicense;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppointmentKey)) return false;
        AppointmentKey k = (AppointmentKey) o;
        return Objects.equals(getDoctor(), k.getDoctor()) &&
                Objects.equals(getClinic(), k.getClinic()) &&
                Objects.equals(getDate(),k.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDoctor(), getClinic());
    }
}
