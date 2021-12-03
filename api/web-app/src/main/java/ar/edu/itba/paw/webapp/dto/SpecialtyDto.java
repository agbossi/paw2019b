package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.model.Specialty;

public class SpecialtyDto {

    private String specialty;

    public static SpecialtyDto fromSpecialty(Specialty specialty) {
        SpecialtyDto dto = new SpecialtyDto();
        dto.specialty = specialty.getSpecialtyName();
        return dto;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
