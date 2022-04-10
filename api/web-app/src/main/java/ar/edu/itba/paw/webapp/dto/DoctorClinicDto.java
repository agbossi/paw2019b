package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.DoctorClinic;

import javax.ws.rs.core.UriInfo;
import java.net.URI;


public class DoctorClinicDto {

    private URI doctor;
    private int consultPrice;
    private int clinicId;
    private String license;
    private URI clinic;
    private URI schedules;

    public static DoctorClinicDto fromDoctorClinic(DoctorClinic doctorClinic, UriInfo uriInfo) {
        DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
        doctorClinicDto.doctor = uriInfo.getBaseUriBuilder().path("doctors")
                .path(doctorClinic.getDoctor().getLicense()).build();
        doctorClinicDto.clinic = uriInfo.getBaseUriBuilder().path("clinics")
                .path(Integer.toString(doctorClinic.getClinic().getId())).build();
        doctorClinicDto.consultPrice = doctorClinic.getConsultPrice();
        doctorClinicDto.clinicId = doctorClinic.getClinic().getId();
        doctorClinicDto.schedules = uriInfo.getBaseUriBuilder().path("doctors")
                .path(doctorClinic.getDoctor().getLicense()).path("clinics")
                .path(Integer.toString(doctorClinic.getClinic().getId()))
                .path("schedules").build();
        doctorClinicDto.license = doctorClinic.getDoctor().getLicense();
        return doctorClinicDto;
    }

    public String getLicense() { return license; }

    public void setLicense(String license) { this.license = license; }

    public URI getDoctor() { return doctor; }

    public void setDoctor(URI doctor) { this.doctor = doctor; }

    public int getClinicId() { return clinicId; }

    public void setClinicId(int clinicId) { this.clinicId = clinicId; }

    public URI getClinic() { return clinic; }

    public void setClinic(URI clinic) { this.clinic = clinic; }

    public URI getSchedules() { return schedules; }

    public void setSchedules(URI schedules) { this.schedules = schedules; }

    public int getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }

}
