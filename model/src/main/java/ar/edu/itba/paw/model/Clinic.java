package ar.edu.itba.paw.model;

public class Clinic {
    private String name;

    // TODO: this should be transformed into an address
    private String city;

    private int consultPrice;

    public Clinic(String name, String city, int consultPrice){
        this.name = name;
        this.city = city;
        this.consultPrice = consultPrice;
    }

    public String getName() {
        return name;
    }

    public String city() {
        return city;
    }

    public int getConsultPrice() {
        return consultPrice;
    }
}
