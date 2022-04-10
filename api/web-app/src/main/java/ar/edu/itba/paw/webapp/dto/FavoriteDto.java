package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Doctor;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class FavoriteDto {
    private String license;
    private URI doctor;

    public static FavoriteDto fromDoctor(Doctor doctor, UriInfo uriInfo) {
        FavoriteDto dto = new FavoriteDto();
        dto.license = doctor.getLicense();
        dto.doctor = uriInfo.getBaseUriBuilder().path("doctors")
                .path(doctor.getLicense()).build();
        return dto;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public URI getDoctor() {
        return doctor;
    }

    public void setDoctor(URI doctor) {
        this.doctor = doctor;
    }
}
