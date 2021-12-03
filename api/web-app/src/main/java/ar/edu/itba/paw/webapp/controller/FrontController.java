package ar.edu.itba.paw.webapp.controller;
/*
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.Appointment;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.SearchForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final int FIRST_PAGE = 0;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @ModelAttribute
    public User loggedUser(){
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User user = userService.findUserByEmail(auth.getName());
        LOGGER.debug("Logged user is {}", user);
        return user;
    }

    // Index shows different jsp depending on user role.
    // This was intended this way in order to preserve correct index view after restarting server
    // when user chose to remember her/his account.
    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form){
        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        if(userService.isDoctor(userEmail)) return doctorIndex(userEmail);
        return patientIndex();
    }

    public ModelAndView doctorIndex(String doctorEmail) {
        Doctor doctor = doctorService.getDoctorByEmail(doctorEmail);
        final ModelAndView mav = new ModelAndView("index");
        List<DoctorClinic> doctorClinics = doctorClinicService.getDoctorClinicsForDoctor(doctor);
        mav.addObject("doctorClinics", doctorClinics);
        List<Appointment> appointments = appointmentService.getAllDoctorsAppointments(doctor);
        mav.addObject("appointments",appointments);
        return mav;
    }

    public ModelAndView patientIndex() {
        final ModelAndView mav = new ModelAndView("index");
        List<String> licenses = doctorService.getAvailableDoctorsLicenses();
        addPaginatedDoctors(mav, licenses, FIRST_PAGE);
        return mav;
    }

    // Private methods for SearchController //

    private void addPaginatedDoctors(ModelAndView mav, List<String> licenses, int page) {
        List<Doctor> doctors = doctorService.getPaginatedDoctors(licenses, page);
        int maxAvailablePage = doctorService.getMaxAvailableDoctorsPage(licenses);
        mav.addObject("doctors", doctors);
        mav.addObject("maxPage", maxAvailablePage);
        mav.addObject("page", page);
    }
} */
