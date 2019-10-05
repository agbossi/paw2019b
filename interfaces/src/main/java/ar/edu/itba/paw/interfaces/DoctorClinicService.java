package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.*;

import java.util.List;

public interface DoctorClinicService {
    DoctorClinic createDoctorClinic(Doctor doctor,  Clinic clinic, int consultPrice);

    void setSchedule(DoctorClinic doctorClinic, int day, int hour);

    List<DoctorClinic> getDoctorClinics();

    List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor);

    List<DoctorClinic> getDoctorsFromClinic(Clinic clinic);

    DoctorClinic getDoctorClinicFromDoctorAndClinic(Doctor doctor, Clinic clinic);

    List<DoctorClinic> getDoctorBy(Location location, Specialty specialty, int clinic);

}
