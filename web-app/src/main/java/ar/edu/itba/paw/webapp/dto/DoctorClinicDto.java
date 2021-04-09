package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.DoctorHour;
import ar.edu.itba.paw.model.Schedule;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

public class DoctorClinicDto {

    private DoctorDto doctorDto;
    private ClinicDto clinicDto;
    private int consultPrice;
    private URI schedules;
    private URI appointments;
    private List<List<DoctorHourDto>> week;

    //TODO ver pathbuilders, estoy llamando desde "/{license}/doctorsClinics" y "/{license}/doctorsClinics/{clinic}"

    //lo que sale en schedules: /web_app_war_exploded/doctors/8895668/doctorsClinics/6/schedules
    //lo que sale en appointments: /web_app_war_exploded/doctors/8895668/doctorsClinics/6/appointments

    public static DoctorClinicDto fromDoctorClinic(DoctorClinic doctorClinic, UriInfo uriInfo,byte[] doctorImage, List<List<DoctorHourDto>> week) {
        DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
        doctorClinicDto.doctorDto = DoctorDto.fromDoctor(doctorClinic.getDoctor(), doctorImage);
        doctorClinicDto.clinicDto = ClinicDto.fromClinic(doctorClinic.getClinic());
        doctorClinicDto.consultPrice = doctorClinic.getConsultPrice();
        doctorClinicDto.schedules = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(doctorClinic.getClinic().getId())).path("schedules").build();
        doctorClinicDto.appointments = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(doctorClinic.getClinic().getId())).path("appointments").build();
        doctorClinicDto.week = week;
        return doctorClinicDto;
    }

    public DoctorDto getDoctorDto() {
        return doctorDto;
    }

    public void setDoctorDto(DoctorDto doctorDto) {
        this.doctorDto = doctorDto;
    }

    public ClinicDto getClinicDto() {
        return clinicDto;
    }

    public void setClinicDto(ClinicDto clinicDto) {
        this.clinicDto = clinicDto;
    }

    public int getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }

    public URI getSchedules() {
        return schedules;
    }

    public void setSchedules(URI schedules) {
        this.schedules = schedules;
    }

    public URI getAppointments() {
        return appointments;
    }

    public void setAppointments(URI appointments) {
        this.appointments = appointments;
    }

    public List<List<DoctorHourDto>> getWeek() {
        return week;
    }

    public void setWeek(List<List<DoctorHourDto>> week) {
        this.week = week;
    }
}
