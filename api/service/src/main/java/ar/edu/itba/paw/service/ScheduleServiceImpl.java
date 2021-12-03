package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.ScheduleDao;
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Transactional
    @Override
    public Schedule createSchedule(int hour, int day, String email, int clinicId) {

        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(
                doctorService.getDoctorByEmail(email),
                clinicService.getClinicById(clinicId));

        if(!doctorHasSchedule(doctorClinic.getDoctor(),day,hour)) {
            return scheduleDao.createSchedule(day, hour, doctorClinic);
        }
        return null; // TODO: change this for optional, check where is it called
    }

    @Override
    public List<Schedule> getDoctorClinicSchedule(DoctorClinic doctorClinic) {
        return scheduleDao.getDoctorClinicSchedule(doctorClinic);
    }

    @Override
    public boolean doctorHasScheduleInClinic(DoctorClinic doctorClinic, int day, int hour) {
        return scheduleDao.doctorHasScheduleInClinic(doctorClinic, day, hour);
    }

    @Transactional
    @Override
    public void deleteSchedule(int hour, int day, String license, int clinicId) {
        if(hour > 0 && hour < 24 && day > 0 && day < 8) {
            DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(
                    doctorService.getDoctorByLicense(license),
                    clinicService.getClinicById(clinicId));

            if(doctorHasSchedule(doctorClinic.getDoctor(), day, hour)) {
                scheduleDao.deleteSchedule(hour, day, doctorClinic);
                appointmentService.cancelAllAppointmentsOnSchedule(doctorClinic, day, hour);
            }
        }
    }

    @Override
    public boolean doctorHasSchedule(Doctor doctor, int day, int hour) {
        return scheduleDao.doctorHasSchedule(doctor,day,hour);
    }
}
