package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.*;

import java.util.List;

public interface DoctorClinicDao {
        DoctorClinic createDoctorClinic(Doctor doctor, Clinic clinic, int consultPrice);

        List<DoctorClinic> getDoctorClinics();

        List<DoctorClinic> getDoctorsInClinic(long clinic);

        DoctorClinic getDoctorInClinic(String doctor, long clinic);

        List<DoctorClinic> getClinicsWithDoctor(String doctor);

        List<DoctorClinic> getFilteredDoctors(Location location, Specialty specialty, long clinic);
}
