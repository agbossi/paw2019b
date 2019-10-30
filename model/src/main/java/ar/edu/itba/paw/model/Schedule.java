package ar.edu.itba.paw.model;

import keys.ScheduleKey;

import javax.persistence.*;


@Entity
@Table(name = "schedule")
public class Schedule {

    @EmbeddedId
    private ScheduleKey scheduleKey;

    //TODO doctor clinic y la key repiten campos?
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "doctor",
                    referencedColumnName = "doctorLicense"),
            @JoinColumn(
                    name = "clinic",
                    referencedColumnName = "clinicid")
    })
    private DoctorClinic doctorClinic;

    public Schedule(int day, int hour,DoctorClinic doctorClinic) {
        this.scheduleKey = new ScheduleKey(day,hour,doctorClinic.getDoctor().getLicense());
        this.doctorClinic = doctorClinic;
    }

    public Schedule(){}

    public int getDay() {
        return getScheduleKey().getDay();
    }

    public void setDay(int day) {
        this.getScheduleKey().setDay(day);
    }

    public int getHour() {
        return getScheduleKey().getHour();
    }

    public ScheduleKey getScheduleKey() {
        return scheduleKey;
    }

    public DoctorClinic getDoctorClinic() {
        return doctorClinic;
    }

    public void setDoctorClinic(DoctorClinic doctorClinic) {
        this.doctorClinic = doctorClinic;
    }

    public void setHour(int hour) {
        this.getScheduleKey().setHour(hour);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Schedule){
            return ((Schedule) obj).getDay() == this.getDay() && ((Schedule) obj).getHour() == this.getHour();
        }
        return false;
    }
}
