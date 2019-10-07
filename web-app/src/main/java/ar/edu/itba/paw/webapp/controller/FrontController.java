package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.webapp.form.SearchForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FrontController {

    @Autowired
    private ModelAndViewModifier viewModifier;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    // Index shows different jsp depending on user role.
    // This was intended this way in order to preserve correct index view after restarting server
    // when user chose to remember her/his account.
    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form){
        final ModelAndView mav = new ModelAndView("index");

        /*
         * What do I need for admin...
         * NOTHING
         * */

        /*
         * What do I need for doctor users...
         * */
        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        if(userService.isDoctor(userEmail)){
            Doctor doctor = doctorService.getDoctorByEmail(userEmail);
            List<DoctorClinic> doctorClinics = doctorClinicService.getDoctorClinicsForDoctor(doctor);
            mav.addObject("doctorClinics", doctorClinics);
            List<Appointment> appointments = appointmentService.getAllDoctorsAppointments(doctor);
            mav.addObject("appointments",appointments);
        }
        else{
            /*
             * What do I need for patients and anonymous users...
             * */
            viewModifier.addSearchInfo(mav);
            // Patients are not interested in doctors that still haven't load their schedule
            viewModifier.addDoctorsWithAvailability(mav);
        }


        return mav;
    }
}
