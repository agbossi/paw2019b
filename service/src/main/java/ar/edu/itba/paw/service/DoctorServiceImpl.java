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
    public List<Doctor> getDoctorByLocation(String location){

    }
}
