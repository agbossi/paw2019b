package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.interfaces.dao.ScheduleDao;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    DoctorClinicDao doctorClinicDao;

    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    AppointmentDao appointmentDao;

    private void setScheduleAndAppointments(List<DoctorClinic> list) {
        if(list != null) {
            for (DoctorClinic doctorClinic: list) {
                List<Schedule> schedules = scheduleDao.getDoctorClinicSchedule(doctorClinic);
                List<Appointment> appointments = appointmentDao.getDoctorsAppointments(doctorClinic);

                doctorClinic.setSchedule(schedules);
                doctorClinic.setAppointments(appointments);
            }
        }
    }

    @Override
    public DoctorClinic createDoctorClinic(Doctor doctor, Clinic clinic, int consultPrice) {
        return doctorClinicDao.createDoctorClinic(doctor, clinic, consultPrice);
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
        Schedule schedule = scheduleDao.createSchedule(day, hour, doctorClinic);
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
    public DoctorClinic getDoctorClinicFromDoctorAndClinic(Doctor doctor, Clinic clinic) {
        DoctorClinic doctorClinic = doctorClinicDao.getDoctorInClinic(doctor.getLicense(), clinic.getId());
        if(doctorClinic != null) {
            List<Schedule> schedules = scheduleDao.getDoctorClinicSchedule(doctorClinic);
            List<Appointment> appointments = appointmentDao.getDoctorsAppointments(doctorClinic);

            doctorClinic.setSchedule(schedules);
            doctorClinic.setAppointments(appointments);
        }

        return doctorClinic;
    }

    @Override
    public List<DoctorClinic> getDoctorBy(Location location, Specialty specialty, int clinic) {
        List<DoctorClinic> list = doctorClinicDao.getFilteredDoctors(location, specialty, clinic);
        setScheduleAndAppointments(list);
        return list;
    }
}
