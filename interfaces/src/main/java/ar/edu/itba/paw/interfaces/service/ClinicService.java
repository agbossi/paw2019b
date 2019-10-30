package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(String name, String address, Location location);

    List<Clinic> getClinics();

    Clinic getClinicByName(String name);

    Clinic getClinicById(int id);

    List<Clinic> getClinicsByLocation(Location location);

    boolean clinicExists(String name,String address,String location);

    void updateClinic(int id, String name, String address, String location);

    long deleteClinic(int id);
}
