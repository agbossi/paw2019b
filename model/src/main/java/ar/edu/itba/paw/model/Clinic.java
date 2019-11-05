package ar.edu.itba.paw.model;

import javax.persistence.*;

@Entity
@Table(name = "clinics")
public class Clinic {

    @Column
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;


    public Clinic(int id, String name, String address, Location location){
        this.id = id;
        this.name = name;
        this.address = address;
        this.location = location;
    }

    public Clinic(){

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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Clinic){
            return ((Clinic) obj).getId() == this.getId();
        }
        return false;
    }
}
