package ar.edu.itba.paw.webapp.controller;
/*
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


@org.springframework.web.bind.annotation.ControllerAdvice(assignableTypes = {SearchController.class,
                                                                             FrontController.class,
                                                                             AdminController.class})
public class SearchAdvice {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private PatientService patientService;

    @ModelAttribute
    public void patientPrepaid(Model model) {
        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(userEmail);
        if(patient != null) {
            model.addAttribute("patientPrepaid", patient.getPrepaid());
        }
    }

    @ModelAttribute
    public void locations(Model model) {
        model.addAttribute("locations", locationService.getLocations());
    }

    @ModelAttribute
    public void specialties(Model model) {
        model.addAttribute("specialties", specialtyService.getSpecialties());
    }

    @ModelAttribute
    public void clinics(Model model) {
        model.addAttribute("clinics", clinicService.getClinics());
    }

    @ModelAttribute
    public void prepaids(Model model) {
        model.addAttribute("prepaids", prepaidService.getPrepaids());
    }
}
*/