package ar.edu.itba.paw.model;

public class Clinic {
    private String name;

    private int id;

    private String address;

    private Location location;


    public Clinic(int id, String name, String address, Location location){
        this.id = id;
        this.name = name;
        this.address = address;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Clinic){
            return ((Clinic) obj).getId() == this.getId();
        }
        return false;
    }
}
