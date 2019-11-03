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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private ValidationHelper validator;

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

        final ModelAndView mav = new ModelAndView("patient/profile");
        mav.addObject("user", user);
        mav.addObject("patient", patient);

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

        validator.passwordValidate(form.getNewPassword(),form.getRepeatPassword(),errors,locale);
        
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


}
