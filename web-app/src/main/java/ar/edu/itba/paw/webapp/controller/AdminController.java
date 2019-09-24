package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.*;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private ModelAndViewModifier viewModifier;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin", method = { RequestMethod.GET })
    public ModelAndView admin(){
        final ModelAndView mav = new ModelAndView("admin");
        return mav;
    }

    @RequestMapping(value = "/addDoctor", method = { RequestMethod.GET })
    public ModelAndView addDoctor(@ModelAttribute("doctorForm") final DoctorForm form){
        final ModelAndView mav = new ModelAndView("addDoctor");

        viewModifier.addSearchInfo(mav);

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
    public ModelAndView addDoctorShedule( @PathVariable(value = "clinicid") int clinic, @PathVariable(value = "license") String license, @ModelAttribute("scheduleForm") final ScheduleForm form){

        final ModelAndView mav = new ModelAndView("doctorSchedule");
        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, cli);

        mav.addObject("doctorClinic", doctorClinic);
        return mav;

    }

    @RequestMapping(value = "/addClinic", method = { RequestMethod.GET })
    public ModelAndView addClinic(@ModelAttribute("clinicForm") final ClinicForm form){
        final ModelAndView mav = new ModelAndView("addClinic");

        viewModifier.addLocations(mav);

        return mav;
    }

    @RequestMapping(value = "/addLocation", method = { RequestMethod.GET })
    public ModelAndView addLocation(@ModelAttribute("locationForm") final LocationForm form){
        final ModelAndView mav = new ModelAndView("addLocation");
        return mav;
    }

    @RequestMapping(value = "/addSpecialty", method = {RequestMethod.GET})
    public ModelAndView addSpecialty(@ModelAttribute("specialtyForm") final SpecialtyForm form){
        final ModelAndView mav = new ModelAndView("addSpecialty");
        return mav;
    }

    @RequestMapping(value = "/addedDoctor", method = { RequestMethod.POST })
    public ModelAndView addedDoctor(@Valid @ModelAttribute("doctorForm") final DoctorForm form, final BindingResult errors) {

        //TODO this is like user controller /signup
        if (!form.getPassword().equals(form.getRepeatPassword())) {
            FieldError passwordNotMatchingError = new FieldError("form", "repeatPassword", "fields password and repeat password do not match");
            errors.addError(passwordNotMatchingError);
        }

        if (userService.userExists(form.getEmail())) {
            //TODO change message for language variable
            FieldError ssoError = new FieldError("form", "email", "That email is already registered");
            errors.addError(ssoError);
        }


        if (errors.hasErrors())
            return addDoctor(form);

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        userService.createUser(form.getFirstName(),form.getLastName(),encodedPassword,form.getEmail());

        doctorService.createDoctor(new Specialty(form.getSpecialty()),
                form.getLicense(),
                form.getPhoneNumber(),form.getEmail()
                );



        final ModelAndView mav = new ModelAndView("addedDoctor");

        return mav;
    }

    @RequestMapping(value = "/addedDoctorClinic", method = { RequestMethod.POST })
    public ModelAndView addedDoctorClinic(@Valid @ModelAttribute("doctorClinicForm") final DoctorClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorClinic(form);

        doctorClinicService.createDoctorClinic(doctorService.getDoctorByLicense(form.getDoctor()),
                clinicService.getClinicById(form.getClinic()),
                form.getConsultPrice());

        final ModelAndView mav = new ModelAndView("addedDoctorClinic");

        return mav;
    }

    @RequestMapping(value = "/addedSchedule/{clinicid}/{license}", method = {RequestMethod.POST})
    public ModelAndView addedSchedule(@PathVariable(value = "clinicid") int clinic, @PathVariable(value = "license") String license,@Valid @ModelAttribute("scheduleForm") final ScheduleForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorShedule(clinic, license, form);
        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, cli);

        scheduleService.createSchedule(form.getHour(), form.getDay(), doctorClinic);

        final ModelAndView mav = new ModelAndView("addedSchedule");

        return mav;
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), new Location(form.getLocation()));

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

    @RequestMapping(value = "/addedSpecialty", method = { RequestMethod.POST })
    public ModelAndView addedSpecialty(@Valid @ModelAttribute("specialtyForm") final SpecialtyForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addSpecialty(form);

        final Specialty specialty = specialtyService.createSpecialty(form.getName());

        final ModelAndView mav = new ModelAndView("addedSpecialty");
        return mav;
    }
}
