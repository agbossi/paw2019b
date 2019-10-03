package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.AppointmentDao;
import ar.edu.itba.paw.interfaces.AppointmentService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    DoctorClinicServiceImpl doctorClinicService;

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

}
