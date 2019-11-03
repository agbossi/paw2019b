package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.UniqueDoctorClinic;

import javax.validation.constraints.Min;

//@UniqueDoctorClinic(clinic = "clinic")
public class DoctorClinicForm {

    private int clinic;

    @Min(0)
    private int consultPrice;

    public int getClinic() {
        return clinic;
    }

    public void setClinic(int clinic) {
        this.clinic = clinic;
    }

    public int getConsultPrice() {
        return consultPrice;
    }

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }
}
