package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Specialty;

import java.util.List;

public interface SpecialtyDao {
    Specialty createSpecialty(String name);

    Specialty getSpecialtyByName(String SpecialtyName);

    List<Specialty> getSpecialties();
}
