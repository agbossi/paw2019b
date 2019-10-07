package ar.edu.itba.paw.model;

public class PrepaidToClinic {

    private Clinic clinic;

    private Prepaid prepaid;

    public PrepaidToClinic(Clinic clinic, Prepaid prepaid) {
        this.clinic = clinic;
        this.prepaid = prepaid;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Prepaid getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Prepaid prepaid) {
        this.prepaid = prepaid;
    }
}
