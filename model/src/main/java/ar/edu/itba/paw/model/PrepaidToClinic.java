package ar.edu.itba.paw.model;

import keys.PrepaidToClinicKey;

import javax.persistence.*;
//TODO que poner si toda la clase es id
@Entity
@Table(name = "clinicPrepaids")

public class PrepaidToClinic {

    //igual que en doctor clinic. son ManyToOne? los joinColumns estan bien puestos?
    //todo: necesito especificar pk si es pk toda la entidad?

    @EmbeddedId
    private PrepaidToClinicKey prepaidToClinicKey;

    @ManyToOne
    @JoinColumn(name = "clinicid",referencedColumnName = "id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "prepaid",referencedColumnName = "name")
    private Prepaid prepaid;

    public PrepaidToClinic(Clinic clinic, Prepaid prepaid) {
        this.clinic = clinic;
        this.prepaid = prepaid;
        this.prepaidToClinicKey = new PrepaidToClinicKey(prepaid.getName(), clinic.getId());
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
