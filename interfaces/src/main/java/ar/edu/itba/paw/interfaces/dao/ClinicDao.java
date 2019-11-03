package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface ClinicDao {
    Clinic createClinic(String name, String address, Location location);

    Clinic getClinicByName(String clinicName);

    List<Clinic> getClinics();

    Clinic getClinicById(int id);

    List<Clinic> getClinicsByLocation(String location);

    boolean clinicExists(String name,String address,String location);

    void updateClinic(int id, String name, String address, String location);

    long deleteClinic(int id);
}
