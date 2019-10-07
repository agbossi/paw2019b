package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;

public class DoctorClinicForm {

    private String doctor;

    private int clinic;

    //TODO custom validation or controller validation
    private int consultPrice;

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

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
