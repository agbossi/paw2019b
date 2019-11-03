package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.UniqueClinic;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//@UniqueClinic(name = "name",address = "address",location = "location")
public class ClinicForm {

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
    private String name;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9 ]+")
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
