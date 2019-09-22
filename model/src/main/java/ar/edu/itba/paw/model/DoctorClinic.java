package ar.edu.itba.paw.model;

import javax.print.Doc;
import java.util.List;

public class DoctorClinic {

    Doctor doctor;

    Clinic clinic;

    int consultPrice;

  //  List<Schedule> schedule;

    public DoctorClinic(Doctor doctor, Clinic clinic, int consultPrice){
        this.doctor = doctor;
        this.clinic = clinic;
        this.consultPrice = consultPrice;
  //      this.schedule = schedule;
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

    public void setConsultPrice(int consultPrice) {
        this.consultPrice = consultPrice;
    }

//    public List<Schedule> getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(List<Schedule> schedule) {
//        this.schedule = schedule;
//    }
}
