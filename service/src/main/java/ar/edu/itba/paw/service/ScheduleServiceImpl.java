package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.ScheduleDao;
import ar.edu.itba.paw.interfaces.ScheduleService;
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
    public Schedule createSchedule(String hour, String day, DoctorClinic doctorClinic) {
        return scheduleDao.createSchedule(hour, day, doctorClinic);
    }

    @Override
    public List<Schedule> getDoctorClinicSchedule(DoctorClinic doctorClinic) {
        return scheduleDao.getDoctorClinicSchedule(doctorClinic);
    }
}
