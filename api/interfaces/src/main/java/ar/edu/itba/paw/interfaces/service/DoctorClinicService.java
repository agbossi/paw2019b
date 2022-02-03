package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Set;

public interface DoctorClinicService {
    DoctorClinic createDoctorClinic(String email, int clinicId, int consultPrice) throws EntityNotFoundException;

    long deleteDoctorClinic(String license, int clinicid) throws EntityNotFoundException;

    void setSchedule(DoctorClinic doctorClinic, int day, int hour);

    List<DoctorClinic> getDoctorClinics();

    List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor);

    List<DoctorClinic> getDoctorsFromClinic(Clinic clinic);

    DoctorClinic getDoctorInClinic(String doctor, int clinic);

    DoctorClinic getDoctorClinicFromDoctorAndClinic(Doctor doctor, Clinic clinic);

    List<DoctorClinic> getFilteredDoctorClinics(Location location, Specialty specialty,
                    String firstName, String lastName, Prepaid prepaid, int consultPrice);

    List<DoctorClinic> getPaginatedDoctorsClinics(Doctor doctor, int page);

    void editPrice(String license, int clinicId, int price) throws EntityNotFoundException;

    int maxAvailablePage();

}
