package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface ClinicDao {
    Clinic createClinic(String name, Location location, int consultPrice);

    public Clinic getClinicByName(String clinicName);

    public List<Clinic> getClinics();
}
