package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.PersonalInformationForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
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
    private DoctorClinicService doctorClinicService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ValidationHelper validator;

    @Autowired
    private ModelAndViewModifier viewModifier;

    private void setFormInformation(PersonalInformationForm form, User user, Patient patient) {
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        if(patient != null) {
            form.setPrepaid(patient.getPrepaid());
            form.setPrepaidNumber(patient.getPrepaidNumber());
        }
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

        viewModifier.addPrepaids(mav);

        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.POST })
    public ModelAndView editedProfile(@Valid @ModelAttribute("personalInformationForm") final PersonalInformationForm form,
                                      final BindingResult errors, Locale locale) {

        System.out.println("valor de contraseña vieja: " + form.getOldPassword() + " " + form.getOldPassword().equals(""));
        System.out.println("valor de contraseña nueva: " + form.getNewPassword() + " " + form.getNewPassword().equals(""));
        System.out.println("valor de confirmar contraseña: " + form.getPrepaidNumber() + " " + form.getPrepaidNumber().equals(""));
        System.out.println("valor de nombre: " + form.getFirstName());
        System.out.println("valor de id: " + form.getId() + " " + form.getId().equals(""));


        validator.passwordEditValidate(form.getOldPassword(),form.getNewPassword(),form.getRepeatPassword(),errors,locale);


        if(errors.hasErrors()){
            return editProfile(form);
        }
        //TODO updating goes here
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
