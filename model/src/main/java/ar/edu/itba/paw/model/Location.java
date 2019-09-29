package ar.edu.itba.paw.model;

import java.util.LinkedList;
import java.util.List;

public class Location {

    private String name;

    private List<Clinic> clinicsInLocation;

    public Location(String name){
        this.name = name;
        this.clinicsInLocation = new LinkedList<>();
    }

    public String getLocationName() {
        return name;
    }

    public List<Clinic> getClinicsInLocation() {
        return clinicsInLocation;
    }

    public void setClinicsInLocation(List<Clinic> clinicsInLocation) {
        this.clinicsInLocation = clinicsInLocation;
    }
}

