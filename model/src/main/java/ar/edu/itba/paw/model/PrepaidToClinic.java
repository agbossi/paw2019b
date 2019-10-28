package ar.edu.itba.paw.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clinicPrepaids")
public class PrepaidToClinic {

    //igual que en doctor clinic. son ManyToOne? los joinColumns estan bien puestos?
    //necesito especificar pk si todo es pk?

    @ManyToOne
    @JoinColumn(name = "id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "name")
    private Prepaid prepaid;

    public PrepaidToClinic(Clinic clinic, Prepaid prepaid) {
        this.clinic = clinic;
        this.prepaid = prepaid;
    }

    public PrepaidToClinic(){}

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
