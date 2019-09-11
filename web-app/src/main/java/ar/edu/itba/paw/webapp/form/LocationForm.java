package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Size;

public class LocationForm {

    @Size(min = 2, max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
