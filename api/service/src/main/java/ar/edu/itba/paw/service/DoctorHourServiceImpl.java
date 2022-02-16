//package ar.edu.itba.paw.service;
//
//import ar.edu.itba.paw.interfaces.service.AppointmentService;
//import ar.edu.itba.paw.interfaces.service.DoctorHourService;
//import ar.edu.itba.paw.interfaces.service.ScheduleService;
//import ar.edu.itba.paw.model.Appointment;
//import ar.edu.itba.paw.model.DoctorClinic;
//import ar.edu.itba.paw.model.DoctorHour;
//import ar.edu.itba.paw.model.Schedule;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class DoctorHourServiceImpl implements DoctorHourService {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @Autowired
//    private ScheduleService scheduleService;
//
//    private static final int START_TIME = 8;
//    private static final int END_TIME = 19;
//
//    @Override
//    public List<List<DoctorHour>> getDoctorsWeek(DoctorClinic doctorClinic, int week) {
//        LocalDate weekBeginning = getFirstWeekday(week);
//        LocalDate weekEnd = weekBeginning.plusDays(4);
//
//        List<Schedule> doctorClinicSchedule = scheduleService.getDoctorClinicSchedule(doctorClinic);
//        List<Appointment> appointmentsWithinWeek = appointmentService.getDoctorAppointmentsWithinWeek(doctorClinic.getDoctor(),
//            weekBeginning, weekEnd);
//
//        List<List<DoctorHour>> doctorWeek = new ArrayList<>();
//        for(int i=START_TIME; i<=END_TIME; i++) {
//            doctorWeek.add(new ArrayList<>());
//        }
//
//        int sch = 0, app = 0;
//        for(int i= DayOfWeek.MONDAY.getValue(); i <= DayOfWeek.FRIDAY.getValue(); i++) {
//            for(int j=START_TIME; j<=END_TIME; j++){
//                LocalDateTime day = getDay(weekBeginning, i, j);
//
//                boolean isSchedule = false;
//                boolean isClinic = false;
//                Appointment appointment = null;
//
//                if(!day.isBefore(LocalDateTime.now())) {
//                    if(appointmentsWithinWeek.size() > 0 && app < appointmentsWithinWeek.size()) {
//                        int cmp = day.compareTo(appointmentsWithinWeek.get(app).getAppointmentKey().getDate());
//                        if(cmp == 0){
//                            appointment = appointmentsWithinWeek.get(app);
//                            app++;
//                        }
//                    }
//                    if(doctorClinicSchedule.size() > 0 && sch < doctorClinicSchedule.size()) {
//                        Schedule schedule = doctorClinicSchedule.get(sch);
//                        int cmp = day.compareTo(getDay(weekBeginning, schedule.getDay()-2, schedule.getHour()));
//                        if(cmp == 0){
//                            isSchedule = isClinic = true;
//                            sch++;
//                        }
//                    }
//                }
//                doctorWeek.get(j-START_TIME).add(new DoctorHour(day, isSchedule, isClinic, appointment));
//            }
//        }
//        return doctorWeek;
//    }
//
//    private LocalDateTime getDay(LocalDate firstDay, int currentDay, int currentHour) {
//        return firstDay.plusDays(currentDay).atTime(currentHour, 0);
//    }
//
//    private LocalDate getFirstWeekday(int week) {
//
//        LocalDate date = LocalDate.now().plusWeeks(week);
//        LocalDate first;
//
//        DayOfWeek weekDay =  date.getDayOfWeek();
//
//        switch (weekDay) {
//            case SUNDAY:
//                first = date.plusDays(1);
//                break;
//            case SATURDAY:
//                first = date.plusDays(2);
//                break;
//            default:
//                first = date.minusDays(weekDay.getValue() - 1);
//                break;
//        }
//
//        return first;
//    }
//}