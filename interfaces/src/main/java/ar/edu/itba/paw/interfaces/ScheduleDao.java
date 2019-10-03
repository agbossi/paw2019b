package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Schedule;

import java.util.List;

public interface ScheduleDao {
    Schedule createSchedule(int hour, int day, DoctorClinic doctorClinic);

    List<Schedule> getDoctorClinicSchedule(DoctorClinic doctorClinic);
}
