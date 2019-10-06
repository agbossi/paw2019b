package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.ClinicService;
import ar.edu.itba.paw.interfaces.service.DoctorClinicService;
import ar.edu.itba.paw.interfaces.service.DoctorHourService;
import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.model.*;
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

    @Autowired
    private DoctorHourService doctorHourService;

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
        //TODO  NOT HERE
        viewModifier.addSearchInfo(mav);


        // TODO: once we implement a proper query builder fix this and search form attributes !!
        List<DoctorClinic> filteredDoctors = doctorClinicService.getDoctorBy(new Location(form.getLocation()),
                new Specialty(form.getSpecialty()), form.getClinic());

        viewModifier.addFilteredDoctors(mav, filteredDoctors);

        return mav;
    }

    @RequestMapping(value = "/results/{license}", method = {RequestMethod.GET})
    public ModelAndView doctorsPage(@PathVariable(value = "license") String license) {

        Doctor doctor = doctorService.getDoctorByLicense(license);

        ModelAndView mav = new ModelAndView("doctorPage");
        mav.addObject("doctor", doctor);
        viewModifier.addDoctorClinicsForDoctor(mav, doctor);

        return mav;
    }

    @RequestMapping(value = "/results/{license}/{clinicId}/{week}", method = {RequestMethod.GET})
    public ModelAndView doctorsSchedulePage(@PathVariable(value = "clinicId") int clinic, @PathVariable(value = "license") String license, @PathVariable(value = "week") int week) {
        ModelAndView mav = new ModelAndView("doctorSchedulePage");


        DoctorClinic doctor = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctorService.getDoctorByLicense(license), clinicService.getClinicById(clinic));

        viewModifier.addSearchInfo(mav);
        viewModifier.addCurrentDates(mav, week);

        List<List<DoctorHour>> doctorsWeek = doctorHourService.getDoctorsWeek(doctor, week);


        mav.addObject("week", doctorsWeek);
        mav.addObject("weekNum", week);
        mav.addObject("doctor", doctor);


        return mav;
    }
}
