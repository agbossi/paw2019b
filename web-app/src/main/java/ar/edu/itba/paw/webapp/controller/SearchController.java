package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.ClinicService;
import ar.edu.itba.paw.interfaces.DoctorClinicService;
import ar.edu.itba.paw.interfaces.DoctorService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.webapp.form.SearchForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ModelAndViewModifier viewModifier;

    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public ModelAndView search(@ModelAttribute("searchForm") final SearchForm form) {

        final ModelAndView mav = new ModelAndView("search");
        viewModifier.addSearchInfo(mav);

        return mav;
    }

    @RequestMapping(value = "/results", method = {RequestMethod.POST})
    public ModelAndView results(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors) {

        if (errors.hasErrors())
            return search(form);

        final ModelAndView mav = new ModelAndView("results");
        viewModifier.addSearchInfo(mav);

        long clinicid = clinicService.getClinicByName(form.getClinic()).getId();
        // TODO: once we implement a proper query builder fix this and search form attributes !!
        List<DoctorClinic> filteredDoctors = doctorClinicService.getDoctorBy(new Location(form.getLocation()),
                new Specialty(form.getSpecialty()), clinicid);

        viewModifier.addFilteredDoctors(mav, filteredDoctors);

        return mav;
    }

    @RequestMapping(value = "/results/{clinicId}/{doctorId}", method = {RequestMethod.GET})
    public ModelAndView doctorsPage(@PathVariable(value = "clinicId") String clinic, @PathVariable(value = "doctorId") String license) {
        ModelAndView mav = new ModelAndView("doctorPage");


        DoctorClinic doctor = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctorService.getDoctorByLicense(license), clinicService.getClinicByName(clinic));

        viewModifier.addSearchInfo(mav);

        mav.addObject("doctor", doctor);

        return mav;
    }
}
