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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorHourService doctorHourService;

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping(value = "/doctors/{page}", method = {RequestMethod.GET})
    public ModelAndView doctors(@PathVariable(value = "page") int page,
                               @ModelAttribute("searchForm") final SearchForm form) {

        final ModelAndView mav = new ModelAndView("base/doctors");
        List<String> licenses = doctorService.getAvailableDoctorsLicenses();
        addPaginatedDoctors(mav, licenses, doctorService, page);

        return mav;
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    public ModelAndView search(@ModelAttribute("searchForm") final SearchForm form) {

        final ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(value = "/results", method = {RequestMethod.GET})
    public ModelAndView backToResults(@ModelAttribute("searchForm") final SearchForm form, HttpServletRequest request){

        final ModelAndView mav = new ModelAndView("results");

        UserContextHelper.loadUserQuery(form,request);
        addFilteredDoctors(mav, form.getLocation(), form.getSpecialty(), form.getFirstName(),
                           form.getLastName(), form.getPrepaid(), form.getConsultPrice());
        return mav;
    }

    @RequestMapping(value = "/results", method = {RequestMethod.POST})
    public ModelAndView results(@Valid @ModelAttribute("searchForm") final SearchForm form, final BindingResult errors, HttpServletRequest request) {

        if (errors.hasErrors())
            return search(form);

        final ModelAndView mav = new ModelAndView("results");
        UserContextHelper.saveUserQuery(form,request);
        mav.addObject("patientPrepaid", form.getPrepaid());
        addFilteredDoctors(mav, form.getLocation(), form.getSpecialty(), form.getFirstName(),
                form.getLastName(), form.getPrepaid(), form.getConsultPrice());

        return mav;
    }

    @RequestMapping(value = "/results/{license}", method = {RequestMethod.GET})
    public ModelAndView doctorsPage(@PathVariable(value = "license") String license) {
        ModelAndView mav = new ModelAndView("doctorPage");

        Doctor doctor = doctorService.getDoctorByLicense(license);
        mav.addObject("doctor", doctor);
        addDoctorClinicsForDoctor(mav, doctor);

        boolean isFav = false;

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(userEmail);
        if(patient != null) {
            isFav = favoriteService.isFavorite(doctor, patient);
        }

        mav.addObject("isFav", isFav);
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

        List<Calendar> month = ViewModifierHelper.getMonth(week);
        List<List<DoctorHour>> doctorsWeek = doctorHourService.getDoctorsWeek(doctor, week);

        mav.addObject("days", month);
        mav.addObject("today", Calendar.getInstance());
        mav.addObject("week", doctorsWeek);
        mav.addObject("weekNum", week);

        return mav;
    }

    // Private methods for SearchController //

    private void addPaginatedDoctors(ModelAndView mav, List<String> licenses,
                                     DoctorService doctorService, int page) {
        List<Doctor> doctors = doctorService.getPaginatedDoctors(licenses, page);
        int maxAvailablePage = doctorService.getMaxAvailableDoctorsPage(licenses);
        mav.addObject("doctors", doctors);
        mav.addObject("maxPage", maxAvailablePage);
        mav.addObject("page", page);
    }

    private void addFilteredDoctors(ModelAndView mav, String location, String specialty,
                                    String firstName,String lastName,
                                    String prepaid,int consultPrice) {
        List<String> licenses = doctorService.getAvailableFilteredLicenses(new Location(location), new Specialty(specialty),
                firstName,lastName, new Prepaid(prepaid), consultPrice);
        List<Doctor> filteredDoctors = new ArrayList<>();
        for(String lic : licenses) {
            filteredDoctors.add(doctorService.getDoctorByLicense(lic));
        }
        mav.addObject("doctors", filteredDoctors);
    }

    private void addDoctorClinicsForDoctor(ModelAndView mav, Doctor doctor) {
        List<DoctorClinic> doctors = doctorClinicService.getDoctorClinicsForDoctor(doctor);
        mav.addObject("doctorClinics", doctors);
    }
}
