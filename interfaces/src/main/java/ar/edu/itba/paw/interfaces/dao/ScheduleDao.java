package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Schedule;

import java.util.Calendar;
import java.util.List;

public interface ScheduleDao {
    Schedule createSchedule(int hour, int day, DoctorClinic doctorClinic);

    List<Schedule> getDoctorClinicSchedule(DoctorClinic doctorClinic);

    boolean doctorHasScheduleInClinic(DoctorClinic doctorClinic, int day, int hour);

    void deleteSchedule(int hour, int day, DoctorClinic doctorClinic);

    public boolean doctorHasSchedule(Doctor doctor, int day, int hour);
}
