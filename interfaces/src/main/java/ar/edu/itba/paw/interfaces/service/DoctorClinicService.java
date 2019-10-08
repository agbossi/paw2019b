package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;

import java.util.List;
import java.util.Set;

public interface DoctorClinicService {
    DoctorClinic createDoctorClinic(Doctor doctor,  Clinic clinic, int consultPrice);

    void setSchedule(DoctorClinic doctorClinic, int day, int hour);

    List<DoctorClinic> getDoctorClinics();

    List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor);

    List<DoctorClinic> getDoctorsFromClinic(Clinic clinic);

    DoctorClinic getDoctorInClinic(String doctor, int clinic);

    DoctorClinic getDoctorClinicFromDoctorAndClinic(Doctor doctor, Clinic clinic);

    List<Doctor> getDoctorBy(Location location, Specialty specialty,
                    String firstName, String lastName, Prepaid prepaid, int consultPrice);

}
