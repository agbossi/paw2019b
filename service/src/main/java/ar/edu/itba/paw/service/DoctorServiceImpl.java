package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.ImageService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

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

        for(Doctor doc : doctors) {
            List<DoctorClinic> doctorsClinics = doctorClinicService.getDoctorClinicsForDoctor(doc);
            for (DoctorClinic dc : doctorsClinics) {
                if ((dc.getSchedule() != null) && !(doctorsWithAvailability.contains(doc))) {
                    doctorsWithAvailability.add(doc);
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
        Doctor doc = getDoctorByLicense(license);
        return userService.deleteUser(doc.getEmail());
    }

    @Transactional
    @Override
    public void updateDoctor(String license, String phoneNumber, String specialty) {
        Map<String,String> args = new HashMap<>();
        args.put("phoneNumber",phoneNumber);
        if(specialty.equals("")) {
            specialty = null;
        }
        args.put("specialty",specialty);
        doctorDao.updateDoctor(license,args);
    }

    @Transactional
    @Override
    public void updateDoctorProfile(
            String email, String newPassword, String firstName, String lastName, // updates user fields
            String license, String phoneNumber, String specialty, // updates doctor fields
            MultipartFile file, Doctor doctor) { // updates image field
        userService.updateUser(email, newPassword, firstName, lastName);
        updateDoctor(license, phoneNumber, specialty);
        imageService.updateProfileImage(file, doctor);
    }

    @Override
    public List<String> getAvailableFilteredLicenses(Location location, Specialty specialty,
                                                             String firstName, String lastName,
                                                             Prepaid prepaid, int consultPrice) {

        List<String> licenses = new ArrayList<>();
        List<DoctorClinic> doctorClinics = doctorClinicService.getFilteredDoctorClinics(location, specialty,
                                                                    firstName, lastName, prepaid, consultPrice);
        for(DoctorClinic dc : doctorClinics) {
            if((!dc.getSchedule().isEmpty()) && !(licenses.contains(dc.getDoctor().getLicense()))) {
                licenses.add(dc.getDoctor().getLicense());
            }
        }
        return licenses;
    }

    @Override
    public List<String> getAvailableDoctorsLicenses() {
        List<String> licenses = new ArrayList<>();
        List<DoctorClinic> doctorClinics = doctorClinicService.getDoctorClinics();
        for(DoctorClinic dc : doctorClinics) {
            if((!dc.getSchedule().isEmpty()) && !(licenses.contains(dc.getDoctor().getLicense()))) {
                licenses.add(dc.getDoctor().getLicense());
            }
        }
        return licenses;
    }

    @Override
    public List<Doctor> getPaginatedDoctors(List<String> licenses, int page) {
        if(page < 0) {
            return new ArrayList<>();
        }
        return doctorDao.getPaginatedDoctorsInList(licenses, page);
    }

    @Override
    public int getMaxAvailableDoctorsPage(List<String> licenses) {
        return doctorDao.maxAvailableDoctorsInListPage(licenses);
    }

    @Override
    public List<Doctor> getPaginatedObjects(int page) {
        if(page < 0) {
            return new ArrayList<>();
        }
        return doctorDao.getPaginatedObjects(page);
    }

    @Override
    public int maxAvailablePage() {
        return doctorDao.maxAvailablePage();
    }
}
