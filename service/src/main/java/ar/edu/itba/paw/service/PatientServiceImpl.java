package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PatientDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

}
