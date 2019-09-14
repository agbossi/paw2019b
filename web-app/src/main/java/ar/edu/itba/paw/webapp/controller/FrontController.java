package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.ClinicService;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.interfaces.LocationService;
import ar.edu.itba.paw.interfaces.SpecialtyService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.webapp.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FrontController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ClinicService clinicService;

    private ModelAndView addSearchInfoToView(ModelAndView mav){
        List<Location> locations = locationService.getLocations();
        List<Specialty> specialties = specialtyService.getSpecialties();
        List<Clinic> clinics = clinicService.getClinics();
        mav.addObject("locations", locations);
        mav.addObject("specialties", specialties);
        mav.addObject("clinics", clinics);

        return mav;
    }

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form){
        final ModelAndView mav = new ModelAndView("index");
        addSearchInfoToView(mav);

        List<Doctor> doctors = doctorService.getDoctors();
        mav.addObject("doctors", doctors);

        return mav;
    }
}
