package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.PersonalInformationForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import ar.edu.itba.paw.webapp.helpers.ViewModifierHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private FavoriteService favoriteService;

    private void setFormInformation(PersonalInformationForm form, User user, Patient patient) {
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setId(patient.getId());
        form.setPrepaid(patient.getPrepaid());
        form.setPrepaidNumber(patient.getPrepaidNumber());
    }

    @RequestMapping(value = "/profile", method = { RequestMethod.GET })
    public ModelAndView profile() {

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        List<Doctor> favorites = patientService.getPatientFavoriteDoctors(patient);

        final ModelAndView mav = new ModelAndView("patient/profile");
        mav.addObject("user", user);
        mav.addObject("patient", patient);
        mav.addObject("favorites",favorites);
        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.GET })
    public ModelAndView editProfile(@ModelAttribute("personalInformationForm") final PersonalInformationForm form) {

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        setFormInformation(form, user, patient);

        final ModelAndView mav = new ModelAndView("patient/editProfile");

        mav.addObject("user", user);
        mav.addObject("patient", patient);
        ViewModifierHelper.addPrepaids(mav, prepaidService);

        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.POST })
    public ModelAndView editedProfile(@Valid @ModelAttribute("personalInformationForm") final PersonalInformationForm form,
                                      final BindingResult errors, Locale locale) {

        //validator.passwordValidate(form.getNewPassword(),form.getRepeatPassword(),errors,locale);
        
        if(errors.hasErrors()){
            return editProfile(form);
        }

        String password = null;
        if(!form.getNewPassword().equals("")){
            password = passwordEncoder.encode(form.getNewPassword());
        }

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        userService.updateUser(user.getEmail(),password,form.getFirstName(),form.getLastName());
        patientService.updatePatient(user.getEmail(),form.getPrepaid(),form.getPrepaidNumber(),form.getId());

        return profile();
    }

    @RequestMapping(value = "/appointments", method = { RequestMethod.GET })
    public ModelAndView appointments() {

        final ModelAndView mav = new ModelAndView("patient/appointments");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        patientService.setAppointments(patient);

        mav.addObject("user", user);
        mav.addObject("patient", patient);

        return mav;
    }

    @RequestMapping(value = "/favorites",  method = { RequestMethod.GET })
    public ModelAndView favorites(){
        final ModelAndView mav = new ModelAndView(("patient/favorites"));

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        List<Doctor> fav = patientService.getPatientFavoriteDoctors(patient);

        mav.addObject("user", user);
        mav.addObject("patient", patient);

        mav.addObject("doctors", fav);

        return mav;


    }

    @RequestMapping(value = "/addFavorite/{doctorId}", method = { RequestMethod.GET })
    public ModelAndView addFavorite(@PathVariable("doctorId") String license){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());

        Doctor doctor = doctorService.getDoctorByLicense(license);

        if(!favoriteService.isFavorite(doctor, patient)){
            favoriteService.create(doctor,patient);
        }

        final ModelAndView mav = new ModelAndView("redirect:/results/" + license);

        return mav;
    }

    @RequestMapping(value = "deleteFavorite/{doctorId}", method = { RequestMethod.GET })
    public ModelAndView deleteFavorite(@PathVariable("doctorId") String license){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());

        Doctor doctor = doctorService.getDoctorByLicense(license);

        if(favoriteService.isFavorite(doctor, patient)){
            favoriteService.deleteFavorite(doctor,patient);
        }

        final ModelAndView mav = new ModelAndView("redirect:/results/" + license);

        return mav;

    }
}
