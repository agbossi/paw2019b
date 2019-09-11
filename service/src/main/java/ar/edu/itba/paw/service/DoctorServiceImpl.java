package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
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
    public Doctor createDoctor(String name, String specialty, String location, String license, String phoneNumber) {
        return doctorDao.createDoctor(name, specialty, location, license, phoneNumber);
    }

    @Override
    public List<Doctor> getDoctors() { return doctorDao.getDoctors(); }

    @Override
    public List<Doctor> getDoctorByLocation(String location){
        return doctorDao.getDoctorByLocation(location);
    }

    @Override
    public List<Doctor> getDoctorByName(String name) {
        return doctorDao.getDoctorByName(name);
    }

    @Override
    public List<Doctor> getDoctorBySpecialty(String specialty) {
        return doctorDao.getDoctorBySpecialty(specialty);
    }

    @Override
    public List<Doctor> getDoctorBy(String location, String specialty, String clinic) {

        String locationFilter, specialtyFilter, clinicFilter;

        // we should change this, too much repetitive code
        if(location.equals(NO_LOCATION))
            locationFilter=null;
        else
            locationFilter=location;
        if(specialty.equals(NO_SPECIALTY))
            specialtyFilter=null;
        else
            specialtyFilter=specialty;
        if(clinic.equals(NO_CLINIC))
            clinicFilter=null;
        else
            clinicFilter=clinic;

        return doctorDao.getFilteredDoctors(locationFilter, specialtyFilter, clinicFilter);

    }

    @Override
    public Doctor getDoctorByLicense(String license) {
        return doctorDao.getDoctorByLicense(license);
    }
}
