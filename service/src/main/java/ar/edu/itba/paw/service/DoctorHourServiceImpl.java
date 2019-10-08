package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.DoctorHourService;
import ar.edu.itba.paw.interfaces.service.ScheduleService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.DoctorHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DoctorHourServiceImpl implements DoctorHourService {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ScheduleService scheduleService;


    @Override
    public List<List<DoctorHour>> getDoctorsWeek(DoctorClinic doctorClinic, int week) {

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, 7 * (week - 1));
        Calendar first;
        List<Calendar> month = new ArrayList<>();
        if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            first = date;
            first.add(Calendar.DATE, 1);
        }else if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            first = date;
            first.add(Calendar.DATE, 2);
        }else{
            first = date;
            first.add(Calendar.DATE, -(date.get(Calendar.DAY_OF_WEEK)) + 2);
        }

        List<List<DoctorHour>> DocWeek = new ArrayList<>();



        for (int i = 8; i < 20; i++){
            DocWeek.add(getHourRow(doctorClinic, first, Calendar.getInstance(), i));
        }
        return DocWeek;
    }

    private List<DoctorHour> getHourRow(DoctorClinic doctorClinic, Calendar first, Calendar today,  int hour){
        List<DoctorHour> row = new ArrayList<>();
        for (int i = 0; i < 5 ; i++){
            Calendar day = Calendar.getInstance();
            day.setTime(first.getTime());
            day.add(Calendar.DATE, i);
            day.set(Calendar.HOUR_OF_DAY, hour);
            day.set(Calendar.MINUTE, 0);
            day.set(Calendar.SECOND, 0);
            day.set(Calendar.MILLISECOND, 0);
            if(today.compareTo(day) < 0) {
                boolean isClinic = scheduleService.doctorHasSchedule(doctorClinic.getDoctor(), Calendar.MONDAY + i, hour);
                boolean isSchedule = scheduleService.doctorHasScheduleInClinic(doctorClinic, Calendar.MONDAY + i, hour);
                Appointment isApp = appointmentService.hasAppointment(doctorClinic, day);
                DoctorHour docHour = new DoctorHour(day, isSchedule,isClinic,  isApp);
                row.add(docHour);
            }else{
                DoctorHour docHour = new DoctorHour(day, false, false, null);
                row.add(docHour);
            }

        }

        return row;
    }


}
