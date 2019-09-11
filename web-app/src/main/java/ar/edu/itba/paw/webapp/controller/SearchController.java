package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "/results",method = RequestMethod.POST)
    public ModelAndView results(@RequestParam Map<String,String> reqPar){

        String location = reqPar.get("location");
        String specialty = reqPar.get("specialty");
        String clinic = reqPar.get("clinic");

        List<Doctor> doctors = doctorService.getDoctorBy(location,specialty,clinic);
        final ModelAndView mav = new ModelAndView("results");
        mav.addObject("location",location);
        mav.addObject("doctors", doctors);
        return mav;
    }

    @RequestMapping("/results/{doctorId}")
    public ModelAndView doctorsPage(@PathVariable(value = "doctorId") String license){
        ModelAndView mav = new ModelAndView("doctorPage");
        Doctor doctor = doctorService.getDoctorByLicense(license);
        mav.addObject(doctor);
        return mav;
    }


}
