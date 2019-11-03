package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.Unique;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SpecialtyForm {

    @Size(min=1, max=30)
    @Pattern(regexp = "[a-zA-Z ]+")
    //@Unique("specialty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
