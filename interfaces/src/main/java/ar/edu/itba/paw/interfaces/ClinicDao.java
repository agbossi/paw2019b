package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;

import java.util.List;

public interface ClinicDao {
    Clinic createClinic(String name, String location, int consultPrice);

    public Clinic getClinicByName(String clinicName);

    public List<Clinic> getClinics();
}
