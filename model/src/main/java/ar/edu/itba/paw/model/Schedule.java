package ar.edu.itba.paw.model;

import java.util.List;

public class Schedule {
    int day;
    int hour;

    public Schedule(int day, int hour) {
        this.day = day;
        this.hour = hour;
    }

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
