package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    DoctorClinicService doctorClinicService;

    @Autowired
    PatientService patientService;

    @Override
    public Appointment createAppointment(DoctorClinic doctorClinic, Patient patient, Calendar date) {
        Calendar today = Calendar.getInstance();
        if (today.compareTo(date) < 0){
            for (Schedule schedule: doctorClinic.getSchedule()) {
                if(date.get(Calendar.DAY_OF_WEEK) == schedule.getDay() && date.get(Calendar.HOUR_OF_DAY) == schedule.getHour()){
                    return appointmentDao.createAppointment(doctorClinic,patient,date);
                }
            }
        }

        return null;
    }

    @Override
    public List<Appointment> getDoctorsAppointments(DoctorClinic doctorClinic) {
        return appointmentDao.getDoctorsAppointments(doctorClinic);
    }

    @Override
    public List<Appointment> getPatientsAppointments(Patient patient) {
        return appointmentDao.getPatientsAppointments(patient);
    }

    @Override
    public void cancelAppointment(DoctorClinic doctorClinic, Patient patient, Calendar date) {
        appointmentDao.cancelAppointment(doctorClinic,patient,date);
    }

    @Override
    public Appointment hasAppointment(DoctorClinic doctorClinic, Calendar date) {
        return appointmentDao.hasAppointment(doctorClinic, date);

    }

    @Override
    public List<Appointment> getAllDoctorsAppointments(Doctor doctor) {
        List<Appointment> appointments = appointmentDao.getAllDoctorsAppointments(doctor);
        return appointments;
    }
}
