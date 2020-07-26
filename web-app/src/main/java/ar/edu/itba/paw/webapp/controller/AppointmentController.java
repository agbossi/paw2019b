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

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        appointmentService.createAppointment(license, clinicId, user.getEmail(), year, month, day, time);

        final ModelAndView mav = new ModelAndView("redirect:/appointments");
        return mav;
    }

    @RequestMapping(value = "/cancelApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView cancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                          @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                          @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        appointmentService.cancelAppointment(license, clinicId, user.getEmail(), year, month, day, time,false);

        final ModelAndView mav = new ModelAndView("redirect:/appointments");
        return mav;
    }

    @RequestMapping(value = "/docCancelApp/{clinicId}/{patient}/{week}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorCancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "patient") String email,
                                                @PathVariable(value = "week") int week,
                                                @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                                @PathVariable(value = "month") int month, @PathVariable(value = "time") int time,
                                                Locale locale){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        appointmentService.cancelAppointment(doctor.getLicense(), clinicId, email, year, month, day, time,true);


        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId + "/" + week);
        return mav;
    }

    @RequestMapping(value = "/doctorApp/{clinicid}/{week}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorMakesAppointment(@PathVariable(value = "clinicid") int clinicId,
                                               @PathVariable(value = "week") int week,
                                               @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                               @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        appointmentService.createAppointment(doctor.getLicense(), clinicId, doctor.getEmail(), year, month, day, time);


        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId +"/" + week);
        return mav;
    }
}

