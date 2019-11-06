package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.SearchForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ViewModifierHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorHourService doctorHourService;

    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public ModelAndView search(@ModelAttribute("searchForm") final SearchForm form) {

        final ModelAndView mav = new ModelAndView("index");

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(userEmail);
        if(patient != null) {
            mav.addObject("patientPrepaid", patient.getPrepaid());
        }
        ViewModifierHelper.addSearchInfo(mav, locationService, specialtyService, clinicService, prepaidService);

        return mav;
    }

    @RequestMapping(value = "/results", method = {RequestMethod.GET})
    public ModelAndView backToResults(@ModelAttribute("searchForm") final SearchForm form, HttpServletRequest request){

        final ModelAndView mav = new ModelAndView("results");

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(userEmail);
        if(patient != null) {
            mav.addObject("patientPrepaid", patient.getPrepaid());
        }

        UserContextHelper.loadUserQuery(form,request);

        ViewModifierHelper.addSearchInfo(mav, locationService, specialtyService, clinicService, prepaidService);

        List<Doctor> filteredDoctors = doctorClinicService.getDoctorBy(new Location(form.getLocation()),
                new Specialty(form.getSpecialty()), form.getFirstName(),form.getLastName(),new Prepaid(form.getPrepaid()),form.getConsultPrice());

        ViewModifierHelper.addFilteredDoctors(mav, filteredDoctors);

        return mav;
    }

    @RequestMapping(value = "/results", method = {RequestMethod.POST})
    public ModelAndView results(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors, HttpServletRequest request) {

        if (errors.hasErrors())
            return search(form);

        final ModelAndView mav = new ModelAndView("results");

        UserContextHelper.saveUserQuery(form,request);

        mav.addObject("patientPrepaid", form.getPrepaid());
        ViewModifierHelper.addSearchInfo(mav, locationService, specialtyService, clinicService, prepaidService);

        List<Doctor> filteredDoctors = doctorClinicService.getDoctorBy(new Location(form.getLocation()),
                new Specialty(form.getSpecialty()), form.getFirstName(),form.getLastName(),new Prepaid(form.getPrepaid()),form.getConsultPrice());

        ViewModifierHelper.addFilteredDoctors(mav, filteredDoctors);

        return mav;
    }

    @RequestMapping(value = "/results/{license}", method = {RequestMethod.GET})
    public ModelAndView doctorsPage(@PathVariable(value = "license") String license) {

        Doctor doctor = doctorService.getDoctorByLicense(license);

        ModelAndView mav = new ModelAndView("doctorPage");
        mav.addObject("doctor", doctor);
        ViewModifierHelper.addDoctorClinicsForDoctor(mav, doctor, doctorClinicService);

        return mav;
    }

    @RequestMapping(value = "/results/{license}/{clinicId}/{week}", method = {RequestMethod.GET})
    public ModelAndView doctorsSchedulePage(@PathVariable(value = "license") String license,
                                            @PathVariable(value = "clinicId") int clinic,
                                            @PathVariable(value = "week") int week) {

        ModelAndView mav = new ModelAndView("doctorSchedulePage");
        DoctorClinic doctor = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctorService.getDoctorByLicense(license),
                              clinicService.getClinicById(clinic));

        mav.addObject("doctorClinic", doctor);
        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(userEmail);
        if(patient != null) {
            mav.addObject("patientPrepaid", patient.getPrepaid());
        }
        ViewModifierHelper.addSearchInfo(mav, locationService, specialtyService, clinicService, prepaidService);
        ViewModifierHelper.addCurrentDates(mav, week);

        List<List<DoctorHour>> doctorsWeek = doctorHourService.getDoctorsWeek(doctor, week);
        mav.addObject("week", doctorsWeek);
        mav.addObject("weekNum", week);

        return mav;
    }
}
