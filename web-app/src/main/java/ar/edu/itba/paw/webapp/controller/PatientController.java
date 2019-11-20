package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.PersonalInformationForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    private FavoriteService favoriteService;


    @RequestMapping(value = "/profile", method = { RequestMethod.GET })
    public ModelAndView profile() {
        return new ModelAndView("patient/profile");
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.GET })
    public ModelAndView editProfile(@ModelAttribute("personalInformationForm") final PersonalInformationForm form) {

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        setFormInformation(form, user, patient);

        final ModelAndView mav = new ModelAndView("patient/editProfile");
        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.POST })
    public ModelAndView editedProfile(@Valid @ModelAttribute("personalInformationForm") final PersonalInformationForm form,
                                      final BindingResult errors) {
        
        if(errors.hasErrors()){
            return editProfile(form);
        }

        String password = null;
        if(!form.getNewPassword().equals("")){
            password = passwordEncoder.encode(form.getNewPassword());
        }
        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        userService.updateUser(user.getEmail(),password,form.getFirstName(),form.getLastName());
        patientService.updatePatient(user.getEmail(),form.getPrepaid(),form.getPrepaidNumber());

        return profile();
    }

    @RequestMapping(value = "/appointments", method = { RequestMethod.GET })
    public ModelAndView appointments() {

        final ModelAndView mav = new ModelAndView("patient/appointments");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        patientService.setAppointments(patient);

        return mav;
    }

    @RequestMapping(value = "/favorites",  method = { RequestMethod.GET })
    public ModelAndView favorites(){
        return new ModelAndView(("patient/favorites"));
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
    public ModelAndView deleteFavorite(@PathVariable("doctorId") String license, HttpServletRequest request){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        Doctor doctor = doctorService.getDoctorByLicense(license);

        if(favoriteService.isFavorite(doctor, patient)){
            favoriteService.deleteFavorite(doctor,patient);
        }

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
}
