package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.interfaces.service.EmailService;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    DoctorClinicService doctorClinicService;

    @Autowired
    PatientService patientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageSource messageSource;

    @Transactional
    @Override
    public Appointment createAppointment(DoctorClinic doctorClinic, User patient, Calendar date) {
        Calendar today = Calendar.getInstance();
        if (today.compareTo(date) < 0){
            for (Schedule schedule: doctorClinic.getSchedule()) {
                if(date.get(Calendar.DAY_OF_WEEK) == schedule.getDay() && date.get(Calendar.HOUR_OF_DAY) == schedule.getHour()){
                    Locale locale = LocaleContextHolder.getLocale();
                    emailService.sendSimpleMail(patient.getEmail(),messageSource.getMessage("appointment.created.subject",null,locale),messageSource.getMessage("appointment.created.text" + " " + date.toString(),null,locale));
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
    public List<Appointment> getPatientsAppointments(User patient) {
        return appointmentDao.getPatientsAppointments(patient);
    }

    @Transactional
    @Override
    public void cancelAppointment(DoctorClinic doctorClinic, User patient, Calendar date) {
        Locale locale = LocaleContextHolder.getLocale();
        emailService.sendSimpleMail(patient.getEmail(),messageSource.getMessage("appointment.cancelled.subject",null,locale),messageSource.getMessage("appointment.cancelled.text" + " " + date.toString(),null,locale));
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

    @Transactional
    @Override
    public void cancelAllAppointmentsOnSchedule(DoctorClinic doctorClinic, int day, int hour) {
        appointmentDao.cancelAllAppointmentsOnSchedule(doctorClinic, day, hour);
    }
}