package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FrontController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/")
    public ModelAndView index(){
        final ModelAndView mav = new ModelAndView("index");

        List<Doctor> doctors = doctorService.getDoctors();
        mav.addObject("doctors", doctors);

        return mav;
    }

}
