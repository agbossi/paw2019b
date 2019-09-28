package ar.edu.itba.paw.model;

public class Patient {
    private String email;

    private String prepaid;

    private String prepaidNumber;

    private String id;

    public Patient(String email,String id ,String prepaid, String prepaidNumber) {
        this.email = email;
        this.id = id;
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

    public String getEmail() {
        return email;
    }
}
