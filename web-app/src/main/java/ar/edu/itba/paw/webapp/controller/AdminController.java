package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.*;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    PrepaidService prepaidService;

    @Autowired
    PrepaidToClinicService prepaidToClinicService;

    @Autowired
    ImageService imageService;
    
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
    @RequestMapping(value = "/addPrepaid",method = { RequestMethod.GET })
    public ModelAndView addPrepaid(@ModelAttribute("prepaidForm") final PrepaidForm form){
        final ModelAndView mav = new ModelAndView("admin/addPrepaid");
        return mav;
    }
    @RequestMapping(value = "/addPrepaidToClinic",method = { RequestMethod.GET })
    public ModelAndView addPrepaidToClinic(@ModelAttribute("prepaidToClinicForm") final PrepaidToClinicForm form){
        final ModelAndView mav = new ModelAndView("admin/addPrepaidToClinic");

        viewModifier.addClinics(mav);
        viewModifier.addPrepaids(mav);
        return mav;
    }

    @RequestMapping(value = "/addedPrepaid",method = { RequestMethod.POST })
    public ModelAndView addedPrepaid(@Valid @ModelAttribute("prepaidForm") final PrepaidForm form,final BindingResult errors){
        if (errors.hasErrors())
            return addPrepaid(form);

        System.out.println(form.getName());
        Prepaid prepaid = prepaidService.createPrepaid(form.getName());
        final ModelAndView mav = new ModelAndView("admin/addedPrepaid");
        mav.addObject("prepaid", prepaid);

        return mav;
    }
    @RequestMapping(value = "/addedPrepaidToClinic",method = { RequestMethod.POST })
    public ModelAndView addedPrepaidToClinic(@Valid @ModelAttribute("prepaidToClinicForm") final PrepaidToClinicForm form,final BindingResult errors){
        if (errors.hasErrors())
            return addPrepaidToClinic(form);


        PrepaidToClinic prepaidToClinic = prepaidToClinicService.addPrepaidToClinic(new Prepaid(form.getPrepaid()),clinicService.getClinicById(form.getClinic()));
        final ModelAndView mav = new ModelAndView("admin/addedPrepaidToClinic");
        mav.addObject("prepaidToClinic", prepaidToClinic);

        return mav;
    }

    @RequestMapping(value = "/addedDoctor", method = { RequestMethod.POST })
    public ModelAndView addedDoctor(@Valid @ModelAttribute("doctorForm") final DoctorForm form, final BindingResult errors,
                                    @RequestParam("photo") MultipartFile photo) {

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
        long image = imageService.createProfileImage(photo, doctor);


        final ModelAndView mav = new ModelAndView("admin/addedDoctor");
        mav.addObject("doctor", doctor);
        mav.addObject("imageId", image);

        return mav;
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), form.getAddress(), new Location(form.getLocation()));

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
