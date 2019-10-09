package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface SpecialtyService {

    Specialty createSpecialty(String name);

    List<Specialty> getSpecialties();

    Specialty getSpecialtyByName(String SpecialtyName);

    long deleteSpecialty(String name);
}
