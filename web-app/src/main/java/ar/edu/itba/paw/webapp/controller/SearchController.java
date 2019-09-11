package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.interfaces.LocationService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.webapp.form.SearchForm;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    LocationService locationService;

    @RequestMapping(value = "/search", method = { RequestMethod.GET })
    public ModelAndView search(@ModelAttribute("searchForm") final SearchForm form){
        ModelAndView mav = new ModelAndView("search");

        List<Location> locations = locationService.getLocations();
        mav.addObject("locations", locations);

        return mav;
    }

    @RequestMapping(value = "/results", method = { RequestMethod.POST })
    public ModelAndView results(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors){

        if(errors.hasErrors())
            return search(form);

        // TODO: once we implement a proper query builder fix this and search form attributes !!
        List<Doctor> doctors = doctorService.getDoctorBy(form.getLocation(), form.getSpecialty(),"noClinic");

        final ModelAndView mav = new ModelAndView("results");
        mav.addObject("location",form.getLocation());
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
