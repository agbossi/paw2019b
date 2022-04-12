package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Appointment;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDateTime;

public class AppointmentDto {

    private UserDto patient;
    private LocalDateTime date;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int dayWeek;
    private int clinicId;
    private String license;
    private URI doctorClinic;

    public static AppointmentDto fromAppointment(Appointment appointment, UriInfo uriInfo) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.patient = appointment.getPatientUser() == null ? null : UserDto.fromUser(appointment.getPatientUser());
        appointmentDto.date = appointment.getAppointmentKey().getDate();
        appointmentDto.year = appointment.getAppointmentKey().getDate().getYear();
        appointmentDto.month = appointment.getAppointmentKey().getDate().getMonthValue();
        appointmentDto.day = appointment.getAppointmentKey().getDate().getDayOfMonth();
        appointmentDto.hour = appointment.getAppointmentKey().getDate().getHour();
        appointmentDto.dayWeek = appointment.getAppointmentKey().getDate().getDayOfWeek().getValue();
        appointmentDto.license = appointment.getDoctorClinic().getDoctor().getLicense();
        appointmentDto.clinicId = appointment.getDoctorClinic().getClinic().getId();
        appointmentDto.doctorClinic = uriInfo.getBaseUriBuilder().path("doctors")
                .path(appointmentDto.license).path("clinics")
                .path(Integer.toString(appointmentDto.clinicId)).build();
        return appointmentDto;
    }

    public int getClinicId() { return clinicId; }

    public void setClinicId(int clinicId) { this.clinicId = clinicId; }

    public String getLicense() { return license; }

    public void setLicense(String license) { this.license = license; }

    public void setDoctorClinic(URI doctorClinic) { this.doctorClinic = doctorClinic; }

    public UserDto getPatient() {
        return patient;
    }

    public void setPatient(UserDto patient) {
        this.patient = patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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

    public int getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
