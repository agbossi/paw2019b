package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Min;

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
