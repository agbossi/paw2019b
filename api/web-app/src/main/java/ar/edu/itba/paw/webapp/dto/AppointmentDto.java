package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Appointment;

import java.util.Calendar;

public class AppointmentDto {

    private UserDto patient;
    private Calendar date;

    /*
    * No dejo info de doctor o clinic porque esta modelado como entidad debil que
    * depende de doctorClinic mediante uri
    */

    public static AppointmentDto fromAppointment(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.patient = UserDto.fromUser(appointment.getPatient());
        appointmentDto.date = appointment.getAppointmentKey().getDate();
        return appointmentDto;
    }

    public UserDto getPatient() {
        return patient;
    }

    public void setPatient(UserDto patient) {
        this.patient = patient;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
