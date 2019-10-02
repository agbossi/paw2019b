package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.DoctorClinicForm;
import ar.edu.itba.paw.webapp.form.ScheduleForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ModelAndViewModifier viewModifier;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public ModelAndView doctorProfile() {
        final ModelAndView mav = new ModelAndView("/doctorProfile");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        mav.addObject("user", user);
        mav.addObject("doctor", doctor);
        return mav;
    }

    @RequestMapping(value = "/addDoctorClinic", method = { RequestMethod.GET })
    public ModelAndView addDoctorClinic(@ModelAttribute("doctorClinicForm") final DoctorClinicForm form){
        final ModelAndView mav = new ModelAndView("addDoctorClinic");

        viewModifier.addClinics(mav);
        viewModifier.addDoctors(mav);

        return mav;
    }

    @RequestMapping(value ="/addSchedule", method = { RequestMethod.GET })
    public ModelAndView addSchedule(){
        final ModelAndView mav = new ModelAndView("addSchedule");

        viewModifier.addDoctorClinics(mav);

        return mav;
    }

    @RequestMapping(value = "addSchedule/{clinicid}/{license}", method = {RequestMethod.GET})
    public ModelAndView addDoctorShedule(@PathVariable(value = "clinicid") int clinic,
                                         @PathVariable(value = "license") String license,
                                         @ModelAttribute("scheduleForm") final ScheduleForm form){

        final ModelAndView mav = new ModelAndView("doctorSchedule");
        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, cli);

        mav.addObject("doctorClinic", doctorClinic);
        return mav;

    }

    @RequestMapping(value = "/addedDoctorClinic", method = { RequestMethod.POST })
    public ModelAndView addedDoctorClinic(@Valid @ModelAttribute("doctorClinicForm") final DoctorClinicForm form,
                                          final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorClinic(form);

        doctorClinicService.createDoctorClinic(doctorService.getDoctorByLicense(form.getDoctor()),
                clinicService.getClinicById(form.getClinic()),
                form.getConsultPrice());

        final ModelAndView mav = new ModelAndView("addedDoctorClinic");

        return mav;
    }

    @RequestMapping(value = "/addedSchedule/{clinicid}/{license}", method = {RequestMethod.POST})
    public ModelAndView addedSchedule(@PathVariable(value = "clinicid") int clinic,
                                      @PathVariable(value = "license") String license,
                                      @Valid @ModelAttribute("scheduleForm") final ScheduleForm form,
                                      final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorShedule(clinic, license, form);
        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, cli);

        scheduleService.createSchedule(form.getHour(), form.getDay(), doctorClinic);

        final ModelAndView mav = new ModelAndView("addedSchedule");

        return mav;
    }
}
