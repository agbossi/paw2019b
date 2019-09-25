package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorServiceImpl implements DoctorService {

    private static final String NO_LOCATION = "noLocation";
    private static final String NO_SPECIALTY = "noSpecialty";
    private static final String NO_CLINIC = "noClinic";

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public Doctor createDoctor(Specialty specialty,String license, String phoneNumber, String email) {
        return doctorDao.createDoctor(specialty,license, phoneNumber,email);
    }

    @Override
    public List<Doctor> getDoctors() { return doctorDao.getDoctors(); }


    @Override
    public List<Doctor> getDoctorByName(String firstName,String lastName) {
        return doctorDao.getDoctorByName(firstName,lastName);
    }

    @Override
    public List<Doctor> getDoctorBySpecialty(Specialty specialty) {
        return doctorDao.getDoctorBySpecialty(specialty);
    }

   /* @Override
    public List<Doctor> getDoctorBy(Location location, Specialty specialty, String clinic) {
        return doctorDao.getFilteredDoctors(location, specialty, "noClinic");
    } */

    @Override
    public Doctor getDoctorByLicense(String license) {
        return doctorDao.getDoctorByLicense(license);
    }

}
