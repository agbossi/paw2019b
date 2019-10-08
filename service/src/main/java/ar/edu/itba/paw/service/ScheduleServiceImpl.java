package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.dao.ScheduleDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.ScheduleService;
import ar.edu.itba.paw.model.Appointment;
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
    ScheduleDao scheduleDao;

    @Autowired
    AppointmentService appointmentService;

    @Transactional
    @Override
    public Schedule createSchedule(int hour, int day, DoctorClinic doctorClinic) {
        return scheduleDao.createSchedule(day, hour, doctorClinic);
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
    public void deleteSchedule(int hour, int day, DoctorClinic doctorClinic) {
        scheduleDao.deleteSchedule(hour, day, doctorClinic);
        appointmentService.cancelAllAppointmentsOnSchedule(doctorClinic, day, hour);
//        List<Appointment> appointments = appointmentDao.getAllDocAppointmentsOnSchedule(doctorClinic, day, hour);
//        for (Appointment a: appointments) {
//            appointmentDao.cancelAppointment(a.getDoctorClinic(), a.getPatient(), a.getDate());
//        }
    }

    @Override
    public boolean doctorHasSchedule(Doctor doctor, int day, int hour) {
        return scheduleDao.doctorHasSchedule(doctor,day,hour);
    }
}
