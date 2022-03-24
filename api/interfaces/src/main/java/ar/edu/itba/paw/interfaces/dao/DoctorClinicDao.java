package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.*;

import java.util.List;

public interface DoctorClinicDao {
        DoctorClinic createDoctorClinic(Doctor doctor, Clinic clinic, int consultPrice);

        long deleteDoctorClinic(String license, int clinicid);

        List<DoctorClinic> getDoctorClinics();

        List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor);

        List<DoctorClinic> getDoctorsInClinic(int clinic);

        DoctorClinic getDoctorInClinic(String doctor, int clinic);

        List<DoctorClinic> getClinicsWithDoctor(String doctor);

        List<DoctorClinic> getFilteredDoctors(Location location, Specialty specialty,
                                              String firstName,String lastName,Prepaid prepaid,int consultPrice);

        List<DoctorClinic> getDoctorClinicPaginatedByList(Doctor doctor, int page);

        int maxPageAvailable();

        void editPrice(DoctorClinic dc, int price);

        List<Doctor> getFilteredDoctors(Location location, Specialty specialty,
                                        String firstName,String lastName,
                                        Prepaid prepaid,int consultPrice, int page);

        List<String> getIdsForSearch(final Location location, final Specialty specialty,
                                     final String firstName, final String lastName, final Prepaid prepaid,
                                     final int consultPrice, int page);
}
