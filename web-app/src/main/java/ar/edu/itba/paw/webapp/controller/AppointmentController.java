package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Locale;

@Controller
public class AppointmentController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/createApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView makeAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                        @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                        @PathVariable(value = "month") int month, @PathVariable(value = "time") int time,
                                        Locale locale){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, time, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Doctor doctor = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, clinic);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);

        if(!ValidationHelper.appointmentValidate(doctor.getLicense(), user.getEmail(), calendar, appointmentService)){
            appointmentService.createAppointment(doctorClinic, user, calendar);
        }

        final ModelAndView mav = new ModelAndView("redirect:/appointments");
        return mav;
    }

    @RequestMapping(value = "/cancelApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView cancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                          @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                          @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, time, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Doctor doctor = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, clinic);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);

        if(ValidationHelper.appointmentValidate(doctor.getLicense(),user.getEmail(),calendar,appointmentService)){
            appointmentService.cancelAppointment(doctorClinic, user, calendar,false);
        }

        final ModelAndView mav = new ModelAndView("redirect:/appointments");
        return mav;
    }

    @RequestMapping(value = "/docCancelApp/{clinicId}/{patient}/{week}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorCancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "patient") String email,
                                                @PathVariable(value = "week") int week,
                                                @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                                @PathVariable(value = "month") int month, @PathVariable(value = "time") int time,
                                                Locale locale){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, time, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        User patient = userService.findUserByEmail(email);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic docCli = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, clinic);

        if(ValidationHelper.appointmentValidate(doctor.getLicense(),patient.getEmail(),calendar,appointmentService)){
            appointmentService.cancelAppointment(docCli, patient, calendar,true);
        }

        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId +"/" + week);
        return mav;
    }

    @RequestMapping(value = "/doctorApp/{clinicid}/{week}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorMakesAppointment(@PathVariable(value = "clinicid") int clinicId,
                                               @PathVariable(value = "week") int week,
                                               @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                               @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, time, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic docClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, clinic);

        if(!ValidationHelper.appointmentValidate(doctor.getLicense(),user.getEmail(),cal,appointmentService)){
            appointmentService.createAppointment(docClinic, user, cal);
        }

        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId +"/" + week);
        return mav;
    }
}

