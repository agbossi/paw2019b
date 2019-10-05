package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.*;
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
@RequestMapping("/admin")
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

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public ModelAndView admin(){
        final ModelAndView mav = new ModelAndView("admin/admin");
        return mav;
    }

    @RequestMapping(value = "/addDoctor", method = { RequestMethod.GET })
    public ModelAndView addDoctor(@ModelAttribute("doctorForm") final DoctorForm form){
        final ModelAndView mav = new ModelAndView("admin/addDoctor");

        viewModifier.addSearchInfo(mav);

        return mav;
    }

    @RequestMapping(value = "/addClinic", method = { RequestMethod.GET })
    public ModelAndView addClinic(@ModelAttribute("clinicForm") final ClinicForm form){
        final ModelAndView mav = new ModelAndView("admin/addClinic");

        viewModifier.addLocations(mav);

        return mav;
    }

    @RequestMapping(value = "/addLocation", method = { RequestMethod.GET })
    public ModelAndView addLocation(@ModelAttribute("locationForm") final LocationForm form){
        final ModelAndView mav = new ModelAndView("admin/addLocation");
        return mav;
    }

    @RequestMapping(value = "/addSpecialty", method = {RequestMethod.GET})
    public ModelAndView addSpecialty(@ModelAttribute("specialtyForm") final SpecialtyForm form){
        final ModelAndView mav = new ModelAndView("admin/addSpecialty");
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
        Doctor doctor = doctorService.createDoctor(new Specialty(form.getSpecialty()),
                                            form.getLicense(),
                                            form.getPhoneNumber(),
                                            form.getEmail()
                                            );


        final ModelAndView mav = new ModelAndView("admin/addedDoctor");
        mav.addObject("doctor", doctor);

        return mav;
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), new Location(form.getLocation()));

        final ModelAndView mav = new ModelAndView("admin/addedClinic");
        mav.addObject("clinic", clinic);

        return mav;
    }

    @RequestMapping(value = "/addedLocation", method = { RequestMethod.POST })
    public ModelAndView addedLocation(@Valid @ModelAttribute("locationForm") final LocationForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addLocation(form);

        final Location location = locationService.createLocation(form.getName());

        final ModelAndView mav = new ModelAndView("admin/addedLocation");
        mav.addObject("location", location);

        return mav;
    }

    @RequestMapping(value = "/addedSpecialty", method = { RequestMethod.POST })
    public ModelAndView addedSpecialty(@Valid @ModelAttribute("specialtyForm") final SpecialtyForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addSpecialty(form);

        final Specialty specialty = specialtyService.createSpecialty(form.getName());

        final ModelAndView mav = new ModelAndView("admin/addedSpecialty");
        mav.addObject("specialty", specialty);

        return mav;
    }
}
