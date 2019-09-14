package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.ClinicService;
import ar.edu.itba.paw.interfaces.LocationService;
import ar.edu.itba.paw.interfaces.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ClinicService clinicService;

    protected ModelAndView addSearchInformationToView(ModelAndView mav){
        mav.addObject("locations", locationService.getLocations());
        mav.addObject("specialties", specialtyService.getSpecialties());
        mav.addObject("clinics", clinicService.getClinics());

        return mav;
    }
}
