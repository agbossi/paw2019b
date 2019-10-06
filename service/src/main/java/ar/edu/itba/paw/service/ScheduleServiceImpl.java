package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.ScheduleDao;
import ar.edu.itba.paw.interfaces.service.ScheduleService;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDao scheduleDao;

    @Override
    public Schedule createSchedule(int hour, int day, DoctorClinic doctorClinic) {
        return scheduleDao.createSchedule(day, hour, doctorClinic);
    }

    @Override
    public List<Schedule> getDoctorClinicSchedule(DoctorClinic doctorClinic) {
        return scheduleDao.getDoctorClinicSchedule(doctorClinic);
    }

    @Override
    public boolean hasSchedule(DoctorClinic doctorClinic, int day, int hour) {
        return scheduleDao.hasSchedule(doctorClinic, day, hour);
    }

    @Override
    public void deleteSchedule(int hour, int day, DoctorClinic doctorClinic) {
        scheduleDao.deleteSchedule(hour, day, doctorClinic);
    }
}
