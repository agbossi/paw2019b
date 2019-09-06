package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HelloWorldController{

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/")
    public ModelAndView helloWorld(){
        final ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/searchPage")
    public ModelAndView searchPage(){
        final ModelAndView mav = new ModelAndView("searchPage");
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
    @RequestMapping(value = "/resultados",method = RequestMethod.POST)
    public ModelAndView results(@RequestParam Map<String,String> reqPar){

        String ubicacion = reqPar.get("selectUbicacion");

        List<Doctor> doctors = doctorService.getDoctorByLocation(ubicacion);
        String dni = reqPar.get("inputDni");
        final ModelAndView mav = new ModelAndView("resultados");
        mav.addObject("ubicacion",ubicacion);
        mav.addObject("dni",dni);
        mav.addObject("doctors", doctors);
        return mav;
    }
}