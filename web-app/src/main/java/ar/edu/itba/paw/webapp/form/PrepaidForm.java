package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.Unique;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PrepaidForm {

    @Size(min=1, max=20)
    @Pattern(regexp = "[a-zA-Z0-9 ]*")
    //@Unique(field = "prepaid")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
