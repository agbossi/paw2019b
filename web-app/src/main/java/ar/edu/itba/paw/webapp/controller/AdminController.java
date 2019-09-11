package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.ClinicService;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.interfaces.LocationService;
import ar.edu.itba.paw.interfaces.SpecialtyService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.webapp.form.ClinicForm;
import ar.edu.itba.paw.webapp.form.DoctorForm;
import ar.edu.itba.paw.webapp.form.LocationForm;
import ar.edu.itba.paw.webapp.form.SpecialtyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @RequestMapping("/admin")
    public ModelAndView admin(){
        final ModelAndView mav = new ModelAndView("admin");
        return mav;
    }

    @RequestMapping(value = "/addDoctor", method = { RequestMethod.GET })
    public ModelAndView addDoctor(@ModelAttribute("doctorForm") final DoctorForm form){
        final ModelAndView mav = new ModelAndView("addDoctor");

        List<Location> locations = locationService.getLocations();
        List<Specialty> specialties = specialtyService.getSpecialties();
        mav.addObject("locations", locations);
        mav.addObject("specialties", specialties);

        return mav;
    }

    @RequestMapping(value = "/addClinic", method = { RequestMethod.GET })
    public ModelAndView addClinic(@ModelAttribute("clinicForm") final ClinicForm form){
        final ModelAndView mav = new ModelAndView("addClinic");

        List<Location> locations = locationService.getLocations();
        mav.addObject("locations", locations);

        return mav;
    }

    @RequestMapping(value = "/addLocation", method = { RequestMethod.GET })
    public ModelAndView addLocation(@ModelAttribute("locationForm") final LocationForm form){
        final ModelAndView mav = new ModelAndView("addLocation");
        return mav;
    }

    @RequestMapping(value = "/addedDoctor", method = { RequestMethod.POST })
    public ModelAndView addedDoctor(@Valid @ModelAttribute("doctorForm") final DoctorForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addDoctor(form);

        doctorService.createDoctor(form.getName(), form.getSpecialty(), form.getLocation(), form.getLicense(), form.getPhoneNumber());

        final ModelAndView mav = new ModelAndView("addedDoctor");

        return mav;
    }

    @RequestMapping(value = "/addSpecialty", method = {RequestMethod.GET})
    public ModelAndView addSpecialty(@ModelAttribute("specialtyForm") final SpecialtyForm form){
        final ModelAndView mav = new ModelAndView("addSpecialty");
        return mav;
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), form.getLocation(), form.getConsultPrice());

        final ModelAndView mav = new ModelAndView("addedClinic");

        return mav;
    }

    @RequestMapping(value = "/addedLocation", method = { RequestMethod.POST })
    public ModelAndView addedLocation(@Valid @ModelAttribute("locationForm") final LocationForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addLocation(form);

        final Location location = locationService.createLocation(form.getName());

        // this could show something more specific, for example, the recently added location.
        // same goes for doctors and clinics and everything you can add
        final ModelAndView mav = new ModelAndView("addedLocation");

        return mav;
    }

    @RequestMapping("/addedSpecialty")
    public ModelAndView addedSpecialty(@Valid @ModelAttribute("specialtyForm") final SpecialtyForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addSpecialty(form);

        final Specialty specialty = specialtyService.createSpecialty(form.getName());

        final ModelAndView mav = new ModelAndView("addedSpecialty");
        return mav;
    }
}
