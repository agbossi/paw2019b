//package ar.edu.itba.paw.webapp.dto;
//
//import ar.edu.itba.paw.model.DoctorHour;
//
//import java.time.LocalDateTime;
//
//public class DoctorHourDto {
//
//    private LocalDateTime date;
//
//    private boolean isScheduled;
//
//    private boolean isClinic;
//
//    private AppointmentDto appointment;
//
//    //TODO: preguntar si hay redundancia entre date y el date de appointment
//
//    public static DoctorHourDto fromDoctorHour(DoctorHour doctorHour) {
//        DoctorHourDto doctorHourDto = new DoctorHourDto();
//        if(doctorHour.hasAppointment()) {
//            doctorHourDto.appointment = AppointmentDto.fromAppointment(doctorHour.getAppointment());
//        } else {
//            doctorHourDto.appointment = null;
//        }
//        doctorHourDto.isClinic = doctorHour.isClinic();
//        doctorHourDto.isScheduled = doctorHour.getScheduled();
//        //date?
//        return doctorHourDto;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public boolean isScheduled() {
//        return isScheduled;
//    }
//
//    public void setScheduled(boolean scheduled) {
//        isScheduled = scheduled;
//    }
//
//    public boolean isClinic() {
//        return isClinic;
//    }
//
//    public void setClinic(boolean clinic) {
//        isClinic = clinic;
//    }
//
//    public AppointmentDto getAppointment() {
//        return appointment;
//    }
//
//    public void setAppointment(AppointmentDto appointment) {
//        this.appointment = appointment;
//    }
//}
