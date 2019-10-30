package ar.edu.itba.paw.model;

import keys.DoctorClinicKey;

import javax.persistence.*;
import javax.print.Doc;
import javax.xml.namespace.QName;
import java.util.List;

@Entity
@Table(name = "doctorClinics")
public class DoctorClinic {

    //TODO estos dos no se si son oneToOne o ManyToOne
    //esto va asi o los annotations dentro de la pk?
    @ManyToOne
    @JoinColumn(name = "license")
    private Doctor doctor;

    //TODO igual que en appointment, esto repite?
    @EmbeddedId
    private DoctorClinicKey doctorClinicKey;

    @ManyToOne
    @JoinColumn(name = "id")
    private Clinic clinic;

    @Column
    private int consultPrice;

    //TODO doctor clinic tiene referencia a schedule aca pero no en tabla? y schedule al reves
    //TODO mapped by?
    @OneToMany(mappedBy = "doctorClinic")
    private List<Schedule> schedule;

    @OneToMany(mappedBy = "doctorClinic")
    private List<Appointment> appointments;

    public DoctorClinic(Doctor doctor, Clinic clinic, int consultPrice){
        this.doctor = doctor;
        this.clinic = clinic;
        this.doctorClinicKey = new DoctorClinicKey(doctor.getLicense(),clinic.getId());
        this.consultPrice = consultPrice;
    }

    public DoctorClinic(){

    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getConsultPrice() {
        return consultPrice;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DoctorClinic){
            return ((DoctorClinic) obj).getDoctor().equals(this.getDoctor()) && ((DoctorClinic) obj).getClinic().equals(this.getClinic());
        }
        return false;
    }
}
