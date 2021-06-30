package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.ClinicExists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.Exists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.ValidAppointment;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ValidAppointment(license = "license", clinic = "clinic", patient = "patient", time = "time", day = "day", month = "month", year = "year")
public class AppointmentForm {

    @Exists(field = "doctor")
    private String license;

    @ClinicExists
    private int clinic;

    @Exists(field = "user")
    private String patient;

    //TODO: horarios
    @Min(9)
    @Max(18)
    private int time;

    private int day;

    private int month;

    private int year;

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

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
