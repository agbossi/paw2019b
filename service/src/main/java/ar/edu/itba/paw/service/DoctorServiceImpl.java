package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.DoctorDao;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;

@Component
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorDao doctorDao;

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
    public List<Doctor> getDoctorByClinic(String clinic){ return doctorDao.getDoctorByClinic(clinic);}

    @Override
    public List<Doctor> getDoctorBy(String location, String specialty, String clinic) {
        if(location.equals("Select location") && specialty.equals("Select specialty") && !clinic.equals("Select clinic")){
            return doctorDao.getDoctorByClinic(clinic);
        }
        if(location.equals("Select location") && !specialty.equals("Select specialty") && clinic.equals("Select clinic")){
            return doctorDao.getDoctorBySpecialty(specialty);
        }
        if(!location.equals("Select location") && specialty.equals("Select specialty") && clinic.equals("Select clinic")){
            return doctorDao.getDoctorByLocation(location);
        }
        //casos con dos me falta la parte de clinic y que me aprueben la bd
        else{
            return doctorDao.getDoctors();
        }

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
