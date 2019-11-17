package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PatientDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.FavoriteService;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.service.UserService;
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
    public void addDoctorToFavorites(Patient patient, Doctor doctor) {
        favoriteService.create(doctor,patient);
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
    public void setAppointments(Patient patient) {
        User user = userService.findUserByEmail(patient.getEmail());
        List<Appointment> appointments = appointmentService.getPatientsAppointments(user);
        patient.setAppointments(appointments);
    }

    @Transactional
    @Override
    public void updatePatient(String email, String prepaid, String prepaidNumber, String id) {

        Map<String,String> args = new HashMap<>();
        if(!(prepaid == null || prepaid.equals("")) ){
            args.put("prepaid",prepaid);
        }

        // In case we want to intentionally set prepaidNumber to null
        // or if we want to update our prepaidNumber
        if(prepaidNumber == null || !(prepaidNumber.equals(""))) {
            args.put("prepaidNumber", prepaidNumber);
        }

        if(!(id == null || id.equals(""))){
            args.put("id",id);
        }
        patientDao.updatePatient(email,args);
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
