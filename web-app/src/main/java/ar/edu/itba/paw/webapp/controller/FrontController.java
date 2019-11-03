package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.SearchForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ViewModifierHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FrontController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

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
            Patient patient = patientService.getPatientByEmail(userEmail);
            if(patient != null) {
                mav.addObject("patientPrepaid", patient.getPrepaid());
            }
            ViewModifierHelper.addSearchInfo(mav, locationService, specialtyService,
                                    clinicService, prepaidService);
            // Patients are not interested in doctors that still haven't load their schedule
            ViewModifierHelper.addDoctorsWithAvailability(mav, doctorService);
        }


        return mav;
    }

    @ModelAttribute
    public User loggedUser(){
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User user = userService.findUserByEmail((String) auth.getName());
        LOGGER.debug("Logged user is {}", user);
        return user;
    }
}
