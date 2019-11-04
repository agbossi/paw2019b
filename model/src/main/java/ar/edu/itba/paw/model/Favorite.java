package ar.edu.itba.paw.model;

import java.util.List;

public class Favorite {

    private List<Patient> patients;

    private Doctor doctor;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Favorite(Doctor doctor) {
        this.doctor = doctor;
    }

    public Favorite(){
    }
}
