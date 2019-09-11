package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface SpecialtyService {

    Specialty createSpecialty(String name);

    List<Specialty> getSpecialties();
}
