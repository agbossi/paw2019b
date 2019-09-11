package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(String name, String location, int consultPrice);

    List<Clinic> getClinics();
}
