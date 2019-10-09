package ar.edu.itba.paw.model;

import javax.print.Doc;
import java.util.List;

public class DoctorClinic {

    Doctor doctor;

    Clinic clinic;

    int consultPrice;

    List<Schedule> schedule;

    List<Appointment> appointments;

    public DoctorClinic(Doctor doctor, Clinic clinic, int consultPrice){
        this.doctor = doctor;
        this.clinic = clinic;
        this.consultPrice = consultPrice;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DoctorClinic){
            return ((DoctorClinic) obj).getDoctor().equals(this.getDoctor()) && ((DoctorClinic) obj).getClinic().equals(this.getClinic());
        }
        return false;
    }
}
