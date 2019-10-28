package keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@Embeddable
public class AppointmentKey implements Serializable {

    @Column(name = "doctor")
    private String doctorLicense;

    @Column(name = "clinic")
    private int clinicId;

    //esto como seria para el tipo de dato?
    @Column(name = "date")
    private Calendar date;

    public AppointmentKey(){

    }

    public AppointmentKey(String doctor,int clinic,Calendar date){
        this.doctorLicense = doctor;
        this.clinicId = clinic;
        this.date = date;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDoctor() {
        return doctorLicense;
    }

    public int getClinic() {
        return clinicId;
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
