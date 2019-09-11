package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.ClinicService;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private LocationService locationService;

    @RequestMapping("/admin")
    public ModelAndView admin(){
        final ModelAndView mav = new ModelAndView("admin");
        return mav;
    }

    @RequestMapping("/addDoctor")
    public ModelAndView addDoctor(){
        final ModelAndView mav = new ModelAndView("addDoctor");
        return mav;
    }

    @RequestMapping("/addClinic")
    public ModelAndView addClinic(){
        final ModelAndView mav = new ModelAndView("addClinic");
        return mav;
    }

    @RequestMapping("/addLocation")
    public ModelAndView addLocation(){
        final ModelAndView mav = new ModelAndView("addLocation");
        return mav;
    }

    @RequestMapping("/addedDoctor")
    public ModelAndView addedDoctor(@RequestParam Map<String, String> reqPar){
        String name = (String) reqPar.get("name");
        String specialty = (String) reqPar.get("specialty");
        String location = (String) reqPar.get("location");
        String license = (String) reqPar.get("license");
        String phoneNumber = (String) reqPar.get("phoneNumber");

        doctorService.createDoctor(name, specialty, location, license, phoneNumber);

        final ModelAndView mav = new ModelAndView("addedDoctor");

        return mav;
    }

    @RequestMapping("/addedClinic")
    public ModelAndView addedClinic(@RequestParam Map<String, String> reqPar){
        String name = (String) reqPar.get("name");
        String location = (String) reqPar.get("location");
        int consultPrice = Integer.valueOf((String) reqPar.get("consultPrice"));

        clinicService.createClinic(name, location, consultPrice);

        final ModelAndView mav = new ModelAndView("addedClinic");

        return mav;
    }

    @RequestMapping("/addedLocation")
    public ModelAndView addedLocation(@RequestParam Map<String, String> reqPar){
        String name = (String) reqPar.get("name");

        locationService.createLocation(name);

        final ModelAndView mav = new ModelAndView("addedLocation");

        return mav;
    }
}
