package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/doctorProfile", method = { RequestMethod.GET })
    public ModelAndView doctorProfile() {
        final ModelAndView mav = new ModelAndView("/doctorProfile");
        //TODO: what to do in case of null
        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        mav.addObject("user", user);
        mav.addObject("doctor", doctor);
        return mav;
    }
}
