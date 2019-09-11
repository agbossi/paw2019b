package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(String name, Location location, int consultPrice);

    List<Clinic> getClinics();
}
