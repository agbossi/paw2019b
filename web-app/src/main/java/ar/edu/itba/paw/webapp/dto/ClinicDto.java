package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Clinic;

public class ClinicDto {

    private int id;
    private String address;
    private String Location;
    private String name;

    //Falta prepaid. va como uri?

    public static ClinicDto fromClinic(Clinic clinic) {
        ClinicDto clinicDto = new ClinicDto();
        clinicDto.address = clinic.getAddress();
        clinicDto.id = clinic.getId();
        clinicDto.Location = clinic.getLocation().getLocationName();
        clinicDto.name = clinic.getName();
        return clinicDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
