package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/")
    public ModelAndView index(){
        final ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/addDoctor")
    public ModelAndView addDoctor(){
        final ModelAndView mav = new ModelAndView("addDoctor");
        return mav;
    }

    @RequestMapping("/added")
    public ModelAndView addedDoctor(@RequestParam Map<String, String> reqPar){
        String name = (String) reqPar.get("name");
        String specialty = (String) reqPar.get("specialty");
        String location = (String) reqPar.get("location");
        String license = (String) reqPar.get("license");

        doctorService.createDoctor(name, specialty, location, license);

        final ModelAndView mav = new ModelAndView("addedDoctor");

        return mav;
    }
    @RequestMapping(value = "/results",method = RequestMethod.POST)
    public ModelAndView results(@RequestParam Map<String,String> reqPar){

        String location = reqPar.get("selectLocation");
        String specialty = reqPar.get("selectSpecialty");
        String clinic = reqPar.get("selectClinic");

        List<Doctor> doctors = doctorService.getDoctorBy(location,specialty,clinic);
        String dni = reqPar.get("inputDni");
        final ModelAndView mav = new ModelAndView("results");
        mav.addObject("location",location);
        mav.addObject("dni",dni);
        mav.addObject("doctors", doctors);
        return mav;
    }

    @RequestMapping("/results/{doctorId}")
    public ModelAndView doctorsPage(@PathVariable(value = "doctorId") String license){
        ModelAndView mav = new ModelAndView("DoctorPage");
        Doctor doctor = doctorService.getDoctorByLicense(license);
        mav.addObject(doctor);
        return mav;
    }
}
