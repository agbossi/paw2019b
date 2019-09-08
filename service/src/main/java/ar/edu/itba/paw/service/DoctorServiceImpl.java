package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorDao doctorDao;

    @Override
    public String[] getDoctors() {
        return new String[]{"alvaro", "jose", "lucas"};
    }

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
    public Doctor getDoctorByLicense(String license) {
        return doctorDao.getDoctorByLicense(license);
    }

    @Override
    public Doctor createDoctor(String name, String specialty, String location, String license) {
        return doctorDao.createDoctor(name, specialty, location, license);
    }
}
