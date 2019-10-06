package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;

public class ClinicForm {

    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String name;

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

}
