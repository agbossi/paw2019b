package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    private DoctorClinicDao doctorClinicDao;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    private void setScheduleAndAppointments(List<DoctorClinic> list) {
        if(list != null) {
            for (DoctorClinic doctorClinic: list) {
                List<Schedule> schedules = scheduleService.getDoctorClinicSchedule(doctorClinic);
                List<Appointment> appointments = appointmentService.getDoctorsAppointments(doctorClinic);

                doctorClinic.setSchedule(schedules);
                doctorClinic.setAppointments(appointments);
            }
        }
    }

    @Transactional
    @Override
    public DoctorClinic createDoctorClinic(String email, int clinicId, int consultPrice) {
        Doctor doctor = doctorService.getDoctorByEmail(email);
        Clinic clinic = clinicService.getClinicById(clinicId);

        return doctorClinicDao.createDoctorClinic(doctor, clinic, consultPrice);
    }

    @Transactional
    @Override
    public long deleteDoctorClinic(String license, int clinicid) {
        return doctorClinicDao.deleteDoctorClinic(license, clinicid);
    }

    @Override
    public List<DoctorClinic> getDoctorClinics() {
        List<DoctorClinic> list = doctorClinicDao.getDoctorClinics();
        setScheduleAndAppointments(list);
        return list;
    }

    @Override
    public List<DoctorClinic> getDoctorClinicsForDoctor(Doctor doctor) {
        List<DoctorClinic> list = doctorClinicDao.getDoctorClinicsForDoctor(doctor);
        setScheduleAndAppointments(list);
        return list;
    }

    @Override
    public void setSchedule(DoctorClinic doctorClinic, int day, int hour) {

        Schedule schedule = scheduleService.createSchedule(day, hour, doctorClinic.getDoctor().getEmail(),
                doctorClinic.getClinic().getId());
        List<Schedule> list = doctorClinic.getSchedule();
        list.add(schedule);
        doctorClinic.setSchedule(list);
    }

    @Override
    public List<DoctorClinic> getDoctorsFromClinic(Clinic clinic) {
        List<DoctorClinic> list = doctorClinicDao.getDoctorsInClinic(clinic.getId());
        setScheduleAndAppointments(list);
        return list;
    }

    @Override
    public DoctorClinic getDoctorInClinic(String doctor, int clinic) {
        return doctorClinicDao.getDoctorInClinic(doctor,clinic);
    }

    @Override
    public DoctorClinic getDoctorClinicFromDoctorAndClinic(Doctor doctor, Clinic clinic) {
        DoctorClinic doctorClinic = doctorClinicDao.getDoctorInClinic(doctor.getLicense(), clinic.getId());
        if(doctorClinic != null) {
            List<Schedule> schedules = scheduleService.getDoctorClinicSchedule(doctorClinic);
            List<Appointment> appointments = appointmentService.getDoctorsAppointments(doctorClinic);

            doctorClinic.setSchedule(schedules);
            doctorClinic.setAppointments(appointments);
        }

        return doctorClinic;
    }

    @Override
    public List<DoctorClinic> getFilteredDoctorClinics(Location location, Specialty specialty,
                                   String firstName, String lastName,
                                   Prepaid prepaid, int consultPrice) {

        List<DoctorClinic> doctorClinics = doctorClinicDao.getFilteredDoctors(location, specialty, firstName,
                lastName, prepaid, consultPrice);
        setScheduleAndAppointments(doctorClinics);
        return doctorClinics;
    }

}
