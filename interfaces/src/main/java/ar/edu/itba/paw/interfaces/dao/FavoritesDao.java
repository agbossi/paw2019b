package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Patient;

import java.util.List;

public interface FavoritesDao {

    void createFavorite(Patient patient, Doctor doctor);

    List<Doctor> getPatientFavorites(Patient patient); //a evaluar lo de la list de doctors
}
