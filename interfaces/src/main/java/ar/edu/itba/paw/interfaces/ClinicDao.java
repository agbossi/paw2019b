package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;

import java.util.List;

public interface ClinicDao {

    public Clinic getClinicByName(String clinic_name);

    public List<Clinic> getClinics();
}
