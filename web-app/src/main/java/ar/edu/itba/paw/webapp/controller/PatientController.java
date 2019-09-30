package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.PersonalInformationForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
    public ModelAndView editProfile(@ModelAttribute("form") final PersonalInformationForm form) {

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());

        setFormInformation(form, user, patient);

        final ModelAndView mav = new ModelAndView("patient/editProfile");
        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.POST })
    public ModelAndView editedProfile(@Valid @ModelAttribute("form") final PersonalInformationForm form,
                                      final BindingResult errors) {

        return editProfile(form);
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

    @RequestMapping(value = "/results/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView makeAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                        @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                        @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, time, 0);

        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        patientService.setAppointments(patient);

        appointmentService.createAppointment(doctorClinic, patient, cal);

        final ModelAndView mav = new ModelAndView("/appointments");

        return mav;

    }

}
