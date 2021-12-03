package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.ClinicExists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.Exists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.UniqueSchedule;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@UniqueSchedule(license = "license", clinic = "clinic", day = "day", hour = "hour", message = "schedule.registered")
public class ScheduleForm {
    @Min(value = 1, message = "schedule.min.day.constraint")
    @Max(value = 7, message = "schedule.max.day.constraint")
    private int day;

    //TODO valores
    @Min(value = 9, message = "schedule.min.hour.constraint")
    @Max(value = 18, message = "schedule.max.hour.constraint")
    private int hour;

    @Exists(field = "doctor", message = "value.not.exists")
    private String license;

    @ClinicExists(message = "value.not.exists")
    private int clinic;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getClinic() {
        return clinic;
    }

    public void setClinic(int clinic) {
        this.clinic = clinic;
    }
}
