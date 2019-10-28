package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoctorServiceImpl implements DoctorService {

    private static final String NO_LOCATION = "noLocation";
    private static final String NO_SPECIALTY = "noSpecialty";
    private static final String NO_CLINIC = "noClinic";

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public Doctor createDoctor(Specialty specialty, String license, String phoneNumber, User user) {
        return doctorDao.createDoctor(specialty,license, phoneNumber,user);
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

    /*@Override
    public List<Doctor> getDoctorBy(Location location, Specialty specialty, String clinic) {
        return doctorDao.getFilteredDoctors(location, specialty, "noClinic");
    }*/

    @Override
    public Doctor getDoctorByLicense(String license) {
        return doctorDao.getDoctorByLicense(license);
    }

    @Override
    public Doctor getDoctorByEmail(String email) {
        return doctorDao.getDoctorByEmail(email);
    }

    @Override
    public List<Doctor> getDoctorsWithAvailability() {

        List<Doctor> doctorsWithAvailability = new ArrayList<>();
        List<Doctor> doctors = getDoctors();
            if (doctors != null) {
                for(Doctor doc : doctors) {
                    List<DoctorClinic> doctorsClinics = doctorClinicService.getDoctorClinicsForDoctor(doc);
                    if(doctorsClinics != null) {
                        for( DoctorClinic dc : doctorsClinics ) {
                            if(dc.getSchedule()!=null && !(doctorsWithAvailability.contains(doc))) {
                                doctorsWithAvailability.add(doc);
                            }
                        }
                    }
            }
        }
        return doctorsWithAvailability;
    }

    @Override
    public boolean isDoctor(String email) {
        return doctorDao.isDoctor(email);
    }

    @Transactional
    @Override
    public long deleteDoctor(String license) {
        return doctorDao.deleteDoctor(license);
    }

    @Transactional
    @Override
    public void updateDoctor(String license, String phoneNumber, String specialty) {
        Map<String,String> args = new HashMap<>();
        args.put("phoneNumber",phoneNumber);
        args.put("specialty",specialty);
        doctorDao.updateDoctor(license,args);
    }
}
