package ar.edu.itba.paw.model;

public class Clinic {
    private String name;

    private int id;
    // TODO: this should be transformed into an address
    private Location location;


    public Clinic(int id, String name, Location location){
        this.id = id;
        this.name = name;
        this.location = location;;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
