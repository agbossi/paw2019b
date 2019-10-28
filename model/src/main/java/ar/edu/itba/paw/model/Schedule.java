package ar.edu.itba.paw.model;

import keys.ScheduleKey;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schedule")
public class Schedule {


    private int day;

    @EmbeddedId
    private ScheduleKey scheduleKey;

    private int hour;

    //los name y referenced estan bien?
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "doctor",
                    referencedColumnName = "license"),
            @JoinColumn(
                    name = "clinic",
                    referencedColumnName = "id")
    })
    private DoctorClinic doctorClinic;

    //el doctor clinic lo agregue. TODO: buscar donde se usa el constructor
    public Schedule(int day, int hour,DoctorClinic doctorClinic) {
        this.day = day;
        this.hour = hour;
        this.doctorClinic = doctorClinic;
    }

    public Schedule(){}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public DoctorClinic getDoctorClinic() {
        return doctorClinic;
    }

    public void setDoctorClinic(DoctorClinic doctorClinic) {
        this.doctorClinic = doctorClinic;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Schedule){
            return ((Schedule) obj).getDay() == this.getDay() && ((Schedule) obj).getHour() == this.getHour();
        }
        return false;
    }
}
