package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;

public class LocationForm {

    @Pattern(regexp = "[a-zA-Z ]+")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
