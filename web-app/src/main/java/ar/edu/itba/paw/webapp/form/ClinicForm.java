package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.NotNull;

public class ClinicForm {

    @NotNull
    private String name;

    @NotNull
    private String address;

    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
