package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.ClinicExists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.Exists;
import ar.edu.itba.paw.webapp.helpers.validation.annotations.UniquePrepaidToClinic;

@UniquePrepaidToClinic(prepaid = "prepaid",clinic = "clinic", message = "value.registered")
public class PrepaidToClinicForm {

    @Exists(field = "prepaid", message = "value.not.exists")
    private String prepaid;

    @ClinicExists(message = "value.not.exists")
    private int clinic;

    public String getPrepaid() {
        return prepaid;
    }

    public int getClinic() {
        return clinic;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public void setClinic(int clinic) {
        this.clinic = clinic;
    }
}
