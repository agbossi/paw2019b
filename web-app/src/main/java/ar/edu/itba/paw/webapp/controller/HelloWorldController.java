package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HelloWorldController{

    @Autowired
    DoctorService doctorService;

    @RequestMapping("/")
    public ModelAndView helloWorld(){
        final ModelAndView mav = new ModelAndView("searchPage");
        return mav;
    }
    @RequestMapping(value = "/resultados",method = RequestMethod.POST)
    public ModelAndView results(@RequestParam Map<String,String> reqPar){

        String[] doctors = doctorService.getDoctors();

        String ubicacion = reqPar.get("selectUbicacion");
        String dni = reqPar.get("inputDni");
        final ModelAndView mav = new ModelAndView("resultados");
        //mav.addObject("ubicacion",ubicacion);
        //mav.addObject("dni",dni);
        mav.addObject("doctors", doctors);
        return mav;
    }
}