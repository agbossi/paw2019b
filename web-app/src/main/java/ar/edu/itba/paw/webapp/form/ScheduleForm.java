package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.ClinicExists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.Exists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.UniqueSchedule;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@UniqueSchedule(license = "license", clinic = "clinic", day = "day", hour = "hour")
public class ScheduleForm {
    @Min(1)
    @Max(7)
    private int day;

    //TODO valores
    @Min(9)
    @Max(18)
    private int hour;

    @Exists(field = "doctor")
    private String license;

    @ClinicExists
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
}
