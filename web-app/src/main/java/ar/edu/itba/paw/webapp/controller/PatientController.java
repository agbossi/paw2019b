package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.PatientService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.PersonalInformationForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

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
        Patient patient = patientService.getPatientById(user.getEmail());

        final ModelAndView mav = new ModelAndView("/profile");
        mav.addObject("user", user);
        mav.addObject("patient", patient);

        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.GET })
    public ModelAndView editProfile(@ModelAttribute("form") final PersonalInformationForm form) {

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientById(user.getEmail());

        setFormInformation(form, user, patient);

        final ModelAndView mav = new ModelAndView("/editProfile");
        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.POST })
    public ModelAndView editedProfile(@Valid @ModelAttribute("form") final PersonalInformationForm form,
                                      final BindingResult errors) {

        return editProfile(form);
    }

    @RequestMapping(value = "/appointments", method = { RequestMethod.GET })
    public ModelAndView appointments() {
        final ModelAndView mav = new ModelAndView("/appointments");
        return mav;
    }

}
