package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.DoctorClinic;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class DoctorClinicDto {

    private DoctorDto doctor;
    private ClinicDto clinic;
    private int consultPrice;
//    private URI schedules;
//    private URI appointments;
//    private List<List<DoctorHourDto>> week;

    //lo que sale en schedules desde doctorsClinic: /web_app_war_exploded/doctors/8895668/doctorsClinics/6/schedules
    //lo que sale en appointments: /web_app_war_exploded/doctors/8895668/doctorsClinics/6/appointments


    public static DoctorClinicDto fromDoctorClinic(DoctorClinic doctorClinic, UriInfo uriInfo) {
        DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
        doctorClinicDto.doctor = DoctorDto.fromDoctor(doctorClinic.getDoctor(), uriInfo);
        doctorClinicDto.clinic = ClinicDto.fromClinic(doctorClinic.getClinic(), uriInfo);
        doctorClinicDto.consultPrice = doctorClinic.getConsultPrice();
//        doctorClinicDto.schedules = uriInfo.getBaseUriBuilder().path("doctors")
//                .path(doctorClinic.getDoctor().getLicense()).path("doctorsClinics")
//                .path(String.valueOf(doctorClinic.getClinic().getId())).path("schedules").build();
//        doctorClinicDto.appointments = uriInfo.getBaseUriBuilder().path("appointments")
//                .path(doctorClinic.getDoctor().getEmail())
//                .path(String.valueOf(doctorClinic.getClinic().getId())).build();
       // doctorClinicDto.week = week;
        return doctorClinicDto;
    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDto doctorDto) {
        this.doctor = doctorDto;
    }

    public ClinicDto getClinic() {
        return clinic;
    }

    public void setClinic(ClinicDto clinic) {
        this.clinic = clinic;
    }

    public int getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }

//    public URI getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(URI schedules) {
//        this.schedules = schedules;
//    }
//
//    public URI getAppointments() {
//        return appointments;
//    }
//
//    public void setAppointments(URI appointments) {
//        this.appointments = appointments;
//    }
}
