package ar.edu.itba.paw.webapp.controller;

/*
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.PersonalInformationForm;
import ar.edu.itba.paw.webapp.helpers.SecurityHelper;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PrepaidService prepaidService;

    @ModelAttribute
    public void prepaids(Model model) {
        model.addAttribute("prepaids", prepaidService.getPrepaids());
    }

    @RequestMapping(value = "/profile", method = { RequestMethod.GET })
    public ModelAndView profile() {
        final ModelAndView mav = new ModelAndView("patient/profile");
        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());

        mav.addObject("user", user);
        mav.addObject("patient", patient);

        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.GET })
    public ModelAndView editProfile(@ModelAttribute("personalInformationForm") final PersonalInformationForm form) {
        final ModelAndView mav = new ModelAndView("patient/editProfile");
        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        setFormInformation(form, user, patient);

        mav.addObject("user", user);
        mav.addObject("patient", patient);

        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.POST })
    public ModelAndView editedProfile(@Valid @ModelAttribute("personalInformationForm") final PersonalInformationForm form,
                                      final BindingResult errors) {
        
        if(errors.hasErrors()){
            return editProfile(form);
        }

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        userService.updateUser(user.getEmail(),
                SecurityHelper.processNewPassword(form.getNewPassword(), passwordEncoder),
                form.getFirstName(),form.getLastName());
        patientService.updatePatient(user.getEmail(),form.getPrepaid(),form.getPrepaidNumber());

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
        List<Doctor> favorites = patientService.getPatientFavoriteDoctors(patient);

        mav.addObject("user", user);
        mav.addObject("patient", patient);
        mav.addObject("favorites",favorites);

        return mav;
    }

    @RequestMapping(value = "/addFavorite/{doctorId}", method = { RequestMethod.GET })
    public ModelAndView addFavorite(@PathVariable("doctorId") String license){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        patientService.addFavorite(user.getEmail(), license);

        final ModelAndView mav = new ModelAndView("redirect:/results/" + license);

        return mav;
    }

    @RequestMapping(value = "/deleteFavorite/{doctorId}", method = { RequestMethod.GET })
    public ModelAndView deleteFavorite(@PathVariable("doctorId") String license, HttpServletRequest request){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        patientService.deleteFavorite(user.getEmail(), license);

        String referer = request.getHeader("Referer");
        final ModelAndView mav = new ModelAndView("redirect:" + referer);
        return mav;
    }

    // Private methods for PatientController //

    private void setFormInformation(PersonalInformationForm form, User user, Patient patient) {
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setPrepaid(patient.getPrepaid());
        form.setPrepaidNumber(patient.getPrepaidNumber());
    }
} */
