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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PrepaidToClinic){
            return ((PrepaidToClinic) obj).getClinic().equals(this.getClinic())
                    && ((PrepaidToClinic) obj).getPrepaid().getName().equals(this.getPrepaid().getName());
        }
        return false;
    }
}
