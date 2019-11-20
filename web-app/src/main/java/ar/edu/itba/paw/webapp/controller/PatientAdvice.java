package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.service.PrepaidService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(assignableTypes = {PatientController.class})
public class PatientAdvice {

    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;

    @Autowired
    PrepaidService prepaidService;

    @ModelAttribute
    public void patientInfo(Model model) {
        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        List<Doctor> favorites = patientService.getPatientFavoriteDoctors(patient);

        model.addAttribute("user", user);
        model.addAttribute("patient", patient);
        model.addAttribute("favorites",favorites);
    }

    @ModelAttribute
    public void prepaids(Model model) {
        model.addAttribute("prepaids", prepaidService.getPrepaids());
    }
}
