package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ClinicForm {

    @Size(min = 2, max = 20)
    private String name;

    // this should have as restriction to be a Location
    @Size(min = 2, max = 30)
    private String location;

    @Min(0)
    private int consultPrice;

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

    public int getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }
}
