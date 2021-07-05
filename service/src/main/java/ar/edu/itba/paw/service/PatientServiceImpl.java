package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PatientDao;
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private DoctorService doctorService;

    private static final String NoPrepaid = "";

    @Transactional
    @Override
    public Patient create(String id, String prepaid, String prepaidNumber, String firstName, String lastName, String password, String email) {
        if(prepaid.equals(NoPrepaid)) {
            prepaid = null;
            prepaidNumber = null;
        }
        User user = userService.createUser(firstName, lastName, password, email);
        return patientDao.create(id, prepaid, prepaidNumber, user);
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return patientDao.getPatientByEmail(email);
    }

    @Override
    public List<Doctor> getPatientFavoriteDoctors(Patient patient) {
        List<Favorite> list = favoriteService.getPatientsFavorite(patient);
        List<Doctor> doctors = new ArrayList<>();
        for(Favorite fav : list){
            doctors.add(fav.getDoctor());
        }
        return doctors;
    }

    @Override
    public void addFavorite(String patientEmail, String license) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        Patient patient = getPatientByEmail(patientEmail);

        if(!favoriteService.isFavorite(doctor, patient)){
            favoriteService.create(doctor,patient);
        }
    }

    @Override
    public void setAppointments(Patient patient) {
        User user = userService.findUserByEmail(patient.getEmail());
        List<Appointment> appointments = appointmentService.getPatientsAppointments(user);
        patient.setAppointments(appointments);
    }

    @Override
    public void deleteFavorite(String patientEmail, String license) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        Patient patient = getPatientByEmail(patientEmail);

        if(favoriteService.isFavorite(doctor, patient)){
            favoriteService.deleteFavorite(doctor,patient);
        }
    }

    @Override
    @Transactional
    public void deletePatient(String email) {
        userService.deleteUser(email);
    }

    @Transactional
    @Override
    public void updatePatient(String email, String prepaid, String prepaidNumber) {

        Map<String,String> args = new HashMap<>();
        if(!(prepaid == null || prepaid.equals("")) ){
            args.put("prepaid",prepaid);
        }

        // In case we want to intentionally set prepaidNumber to null
        // or if we want to update our prepaidNumber
        if(prepaidNumber == null || !(prepaidNumber.equals(""))) {
            args.put("prepaidNumber", prepaidNumber);
        }

        patientDao.updatePatient(email,args);
    }

    @Transactional
    @Override
    public void updatePatientProfile(String email, String newPassword, String firstName, String lastName, String prepaid, String prepaidNumber) {
        userService.updateUser(email, newPassword, firstName, lastName);
        updatePatient(email, prepaid, prepaidNumber);
    }

    @Override
    public List<Patient> getPatientsByPrepaid(String prepaid) {
        return patientDao.getPatientsByPrepaid(prepaid);
    }

    @Override
    public List<Patient> getPatientsById(String id){
        return patientDao.getPatientsById(id);
    }

}
