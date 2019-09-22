package ar.edu.itba.paw.model;

public class Clinic {
    private String name;

    // TODO: this should be transformed into an address
    private Location location;


    public Clinic(String name, Location location){
        this.name = name;
        this.location = location;;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

}
