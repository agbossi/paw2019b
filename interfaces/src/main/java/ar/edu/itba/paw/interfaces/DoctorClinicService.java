package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.*;
import javafx.scene.transform.Scale;

import java.util.List;

public interface DoctorClinicService {
    DoctorClinic createDoctorClinic(Doctor doctor,  Clinic clinic, int consultPrice);

    void setSchedule(DoctorClinic doctorClinic, String day, String hour);

    List<DoctorClinic> getDoctorClinics();

    List<DoctorClinic> getDoctorsFromClinic(Clinic clinic);

    DoctorClinic getDoctorClinicFromDoctorAndClinic(Doctor doctor, Clinic clinic);

    List<DoctorClinic> getDoctorBy(Location location, Specialty specialty, long clinic);

}
