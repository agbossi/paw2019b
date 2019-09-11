package ar.edu.itba.paw.model;

public class Clinic {
    private String name;

    // TODO: this should be transformed into an address
    private Location location;

    private int consultPrice;

    public Clinic(String name, Location location, int consultPrice){
        this.name = name;
        this.location = location;
        this.consultPrice = consultPrice;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getConsultPrice() {
        return consultPrice;
    }
}
