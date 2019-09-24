package ar.edu.itba.paw.model;

public class Patient {
    private String email;

    private String prepaid;

    private String prepaidNumber;

    public Patient(String email, String prepaid, String prepaidNumber) {
        this.email = email;
        this.prepaid = prepaid;
        this.prepaidNumber = prepaidNumber;
    }

    public String getId() {
        return email;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public String getPrepaidNumber() {
        return prepaidNumber;
    }
}
