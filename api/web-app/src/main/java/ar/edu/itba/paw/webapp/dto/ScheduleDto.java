package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Schedule;

public class ScheduleDto {

    private int day;

    private int hour;

    /*
    * lo mismo que appointments, si asumo que ya tengo doctorClinic, el resto es redundante
    * */

    public static ScheduleDto fromSchedule(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.day = schedule.getDay();
        scheduleDto.hour = schedule.getHour();
        return scheduleDto;
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
