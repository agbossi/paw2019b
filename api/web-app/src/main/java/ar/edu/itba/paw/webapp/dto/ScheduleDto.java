package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Schedule;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class ScheduleDto {

    private int day;

    private int hour;

    private int clinicId;

    private URI clinic;

    public static ScheduleDto fromSchedule(Schedule schedule, UriInfo uriInfo) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.day = schedule.getDay();
        scheduleDto.hour = schedule.getHour();
        scheduleDto.clinicId = schedule.getClinic();
        scheduleDto.clinic = uriInfo.getBaseUriBuilder().path("clinics")
                .path(Integer.toString(scheduleDto.clinicId)).build();
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

    public int getClinicId() { return clinicId; }

    public void setClinicId(int clinicId) { this.clinicId = clinicId; }

    public URI getClinic() { return clinic; }

    public void setClinic(URI clinic) { this.clinic = clinic; }
}
