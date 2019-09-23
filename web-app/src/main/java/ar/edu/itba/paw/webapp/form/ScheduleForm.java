package ar.edu.itba.paw.webapp.form;

public class ScheduleForm {

    private String doctor;

    private int clinic;

    private String day;

    private String hour;

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getClinic() {
        return clinic;
    }

    public void setClinic(int clinic) {
        this.clinic = clinic;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
