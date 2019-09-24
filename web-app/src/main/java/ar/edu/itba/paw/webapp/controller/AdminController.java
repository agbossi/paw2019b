package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import ar.edu.itba.paw.model.Specialty;
import ar.edu.itba.paw.webapp.form.ClinicForm;
import ar.edu.itba.paw.webapp.form.DoctorForm;
import ar.edu.itba.paw.webapp.form.LocationForm;
import ar.edu.itba.paw.webapp.form.SpecialtyForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    private ModelAndViewModifier viewModifier;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public ModelAndView addedDoctor(@Valid @ModelAttribute("doctorForm") final DoctorForm form, final BindingResult errors){

        //TODO this is like user controller /signup
        if (form.getPassword().equals(form.getRepeatPassword())) {
        } else {
            FieldError passwordNotMatchingError = new FieldError("form","repeat password","fields password and repeat password do not match");
            errors.addError(passwordNotMatchingError);
        }

        if (userService.userExists(form.getEmail())) {
            //TODO change message for language variable
            FieldError ssoError = new FieldError("form", "email","That email is already registered" );
            errors.addError(ssoError);
        }


        if(errors.hasErrors())
            return addDoctor(form);

        doctorService.createDoctor(new Specialty(form.getSpecialty()),
                form.getLicense(),
                form.getPhoneNumber(),form.getEmail()
                );

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        userService.createUser(form.getFirstName(),form.getLastName(),encodedPassword,form.getEmail());

        final ModelAndView mav = new ModelAndView("addedDoctor");

        return mav;
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), new Location(form.getLocation()), form.getConsultPrice());

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
