package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public Appointment createAppointment(String license, int clinicId, String userEmail, int year, int month, int day, int time) {
        Calendar today = Calendar.getInstance();

        Calendar date = Calendar.getInstance();
        date.set(year, month, day, time, 0, 0);
        date.set(Calendar.MILLISECOND, 0);

        Doctor doctor = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, clinic);


        User user = userService.findUserByEmail(userEmail);

        if(!hasAppointment(license , userEmail ,date)) {
            if (today.compareTo(date) < 0) {
                for (Schedule schedule : doctorClinic.getSchedule()) {
                    if (date.get(Calendar.DAY_OF_WEEK) == schedule.getDay() && date.get(Calendar.HOUR_OF_DAY) == schedule.getHour()) {
                        Locale locale = LocaleContextHolder.getLocale();
                        emailService.sendSimpleMail(
                                userEmail,
                                messageSource.getMessage("appointment.created.subject", null, locale),
                                messageSource.getMessage("appointment.created.text", null, locale) + " " + dateString(date));
                        return appointmentDao.createAppointment(doctorClinic, user, date);
                    }
                }
            }
            return null;
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
    public void cancelAppointment(String license, int clinicId, String userEmail, int year, int month, int day, int time, boolean cancelledByDoctor) {

        Calendar date = Calendar.getInstance();
        date.set(year, month, day, time, 0, 0);
        date.set(Calendar.MILLISECOND, 0);

        Doctor doctor = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, clinic);

        User user = userService.findUserByEmail(userEmail);

        Locale locale = LocaleContextHolder.getLocale();
        if(hasAppointment(license , userEmail ,date)) {
            if (cancelledByDoctor) {
                emailService.sendSimpleMail(
                        userEmail,
                        messageSource.getMessage("appointment.cancelled.subject", null, locale),
                        messageSource.getMessage(
                                "appointment.cancelled.by.doctor.text", null, locale) +
                                " " + dateString(date));
            } else {
                emailService.sendSimpleMail(
                        doctor.getEmail(),
                        messageSource.getMessage("appointment.cancelled.subject", null, locale),
                        messageSource.getMessage(
                                "appointment.cancelled.by.patient.text", null, locale) +
                                " " + user.getFirstName() + " " + user.getLastName() +
                                " " + dateString(date));
            }
            appointmentDao.cancelAppointment(doctorClinic, user, date);
        }
    }

    @Override
    public Appointment hasAppointment(DoctorClinic doctorClinic, Calendar date) {
        return appointmentDao.hasAppointment(doctorClinic, date);
    }

    @Override
    public boolean hasAppointment(String doctorLicense, String patientEmail, Calendar date) {
        return appointmentDao.hasAppointment(doctorLicense,patientEmail,date);
    }

    @Override
    public List<Appointment> getAllDoctorsAppointments(Doctor doctor) {
        return appointmentDao.getAllDoctorsAppointments(doctor);
    }

    @Override
    public List<Appointment> getDoctorAppointmentsWithinWeek(Doctor doctor, Calendar beginning, Calendar end){
        return appointmentDao.getDoctorAppointmentsWithinWeek(doctor, beginning, end);
    }

    @Transactional
    @Override
    public void cancelAllAppointmentsOnSchedule(DoctorClinic doctorClinic, int day, int hour) {
        appointmentDao.cancelAllAppointmentsOnSchedule(doctorClinic, day, hour);
    }

    private String dateString(Calendar calendar){
        Date date = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
