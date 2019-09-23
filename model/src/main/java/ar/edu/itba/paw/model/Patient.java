package ar.edu.itba.paw.model;

public class Patient {
    private String id;

    private String prepaid;

    private String prepaidNumber;

    public Patient(String id, String prepaid, String prepaidNumber) {
        this.id = id;
        this.prepaid = prepaid;
        this.prepaidNumber = prepaidNumber;
    }

    public String getId() {
        return id;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public String getPrepaidNumber() {
        return prepaidNumber;
    }
}
