package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.DoctorHourService;
import ar.edu.itba.paw.interfaces.service.ScheduleService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.DoctorHour;
import ar.edu.itba.paw.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DoctorHourServiceImpl implements DoctorHourService {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ScheduleService scheduleService;

    private static final int DAYS_IN_A_WEEK = 7;
    private static final int START_TIME = 8;
    private static final int END_TIME = 19;
    private static final int MONDAY = 0; // Monday according to Calendar rules
    private static final int FRIDAY = 4; // Friday according to Calendar rules


    @Override
    public List<List<DoctorHour>> getDoctorsWeek(DoctorClinic doctorClinic, int week) {
        Calendar first = getFirstWeekday(week);

        Calendar weekBeginning = Calendar.getInstance();
        weekBeginning.setTime(first.getTime());
        Calendar weekEnd = Calendar.getInstance();
        weekEnd.setTime(first.getTime());
        weekEnd.add(Calendar.DATE, 4);

        List<Schedule> doctorClinicSchedule = scheduleService.getDoctorClinicSchedule(doctorClinic);
        List<Appointment> appointmentsWithinWeek = appointmentService.getDoctorAppointmentsWithinWeek(doctorClinic.getDoctor(),
            weekBeginning, weekEnd);

        List<List<DoctorHour>> doctorWeek = new ArrayList<>();
        for(int i=START_TIME; i<=END_TIME; i++) {
            doctorWeek.add(new ArrayList<DoctorHour>());
        }

        int sch = 0, app = 0;
        for(int i=MONDAY; i<=FRIDAY; i++) {
            for(int j=START_TIME; j<=END_TIME; j++){
                Calendar day = getDay(first, i, j);

                boolean isSchedule = false;
                boolean isClinic = false;
                Appointment appointment = null;

                if(day.compareTo(Calendar.getInstance()) >= 0) {
                    if(appointmentsWithinWeek.size() > 0 && app < appointmentsWithinWeek.size()) {
                        int cmp = day.compareTo(appointmentsWithinWeek.get(app).getAppointmentKey().getDate());
                        if(cmp == 0){
                            appointment = appointmentsWithinWeek.get(app);
                            app++;
                        }
                    }
                    if(doctorClinicSchedule.size() > 0 && sch < doctorClinicSchedule.size()) {
                        Schedule schedule = doctorClinicSchedule.get(sch);
                        int cmp = day.compareTo(getDay(first, schedule.getDay()-2, schedule.getHour()));
                        if(cmp == 0){
                            isSchedule = isClinic = true;
                            sch++;
                        }
                    }
                }
                doctorWeek.get(j-START_TIME).add(new DoctorHour(day, isSchedule, isClinic, appointment));
            }
        }
        return doctorWeek;
    }

    private Calendar getDay(Calendar firstDay, int currentDay, int currentHour) {
        Calendar day = Calendar.getInstance();
        day.setTime(firstDay.getTime());
        day.add(Calendar.DATE, currentDay);
        day.set(Calendar.HOUR_OF_DAY, currentHour);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day;
    }

    private Calendar getFirstWeekday(int week) {
        Calendar date = Calendar.getInstance();
        // sets date to be today
        date.add(Calendar.DATE, DAYS_IN_A_WEEK * (week - 1));
        Calendar first = date;

        int weekDay = date.get(Calendar.DAY_OF_WEEK);

        if(weekDay == Calendar.SUNDAY){
            // adds one day to the calendar in order for the day to be Monday
            first.add(Calendar.DATE, 1);
        }else if(weekDay == Calendar.SATURDAY){
            // adds two days to the calendar in order for the day to be Monday
            first.add(Calendar.DATE, 2);
        }else{
            first.add(Calendar.DATE, -(date.get(Calendar.DAY_OF_WEEK)) + 2);
        }
        return first;
    }
}