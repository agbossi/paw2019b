package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.*;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
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
    private DoctorClinicService doctorClinicService;

    @Autowired
    private ModelAndViewModifier viewModifier;

    @Autowired
    private ScheduleService scheduleService;


    @RequestMapping(value = "/admin", method = { RequestMethod.GET })
    public ModelAndView admin(){
        final ModelAndView mav = new ModelAndView("admin");
        return mav;
    }

    @RequestMapping(value = "/addDoctor", method = { RequestMethod.GET })
    public ModelAndView addDoctor(@ModelAttribute("doctorForm") final DoctorForm form){
        final ModelAndView mav = new ModelAndView("addDoctor");

        viewModifier.addSearchInfo(mav);

        return mav;
    }

    @RequestMapping(value = "/addDoctorClinic", method = { RequestMethod.GET })
    public ModelAndView addDoctorClinic(@ModelAttribute("doctorClinicForm") final DoctorClinicForm form){
        final ModelAndView mav = new ModelAndView("addDoctorClinic");

        viewModifier.addClinics(mav);
        viewModifier.addDoctors(mav);

        return mav;
    }

    @RequestMapping(value ="/addSchedule", method = { RequestMethod.GET })
    public ModelAndView addSchedule(@ModelAttribute("scheduleForm") final ScheduleForm form){
        final ModelAndView mav = new ModelAndView("addSchedule");

        viewModifier.addDoctorClinics(mav);

        return mav;
    }

    @RequestMapping(value = "/addClinic", method = { RequestMethod.GET })
    public ModelAndView addClinic(@ModelAttribute("clinicForm") final ClinicForm form){
        final ModelAndView mav = new ModelAndView("addClinic");

        viewModifier.addLocations(mav);

        return mav;
    }

    @RequestMapping(value = "/addLocation", method = { RequestMethod.GET })
    public ModelAndView addLocation(@ModelAttribute("locationForm") final LocationForm form){
        final ModelAndView mav = new ModelAndView("addLocation");
        return mav;
    }

    @RequestMapping(value = "/addSpecialty", method = {RequestMethod.GET})
    public ModelAndView addSpecialty(@ModelAttribute("specialtyForm") final SpecialtyForm form){
        final ModelAndView mav = new ModelAndView("addSpecialty");
        return mav;
    }

    @RequestMapping(value = "/addedDoctor", method = { RequestMethod.POST })
    public ModelAndView addedDoctor(@Valid @ModelAttribute("doctorForm") final DoctorForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addDoctor(form);


        doctorService.createDoctor(form.getName(),
                new Specialty(form.getSpecialty()),
                form.getLicense(),
                form.getPhoneNumber());

        final ModelAndView mav = new ModelAndView("addedDoctor");

        return mav;
    }

    @RequestMapping(value = "/addedDoctorClinic", method = { RequestMethod.POST })
    public ModelAndView addedDoctorClinic(@Valid @ModelAttribute("doctorClinicForm") final DoctorClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorClinic(form);

        doctorClinicService.createDoctorClinic(doctorService.getDoctorByLicense(form.getDoctor()),
                clinicService.getClinicById(form.getClinic()),
                form.getConsultPrice());

        final ModelAndView mav = new ModelAndView("addedDoctorClinic");

        return mav;
    }

    @RequestMapping(value = "/addedSchedule", method = {RequestMethod.POST})
    public ModelAndView addedSchedule(@Valid @ModelAttribute("scheduleForm") final ScheduleForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addSchedule(form);
        Doctor doc = doctorService.getDoctorByLicense(form.getDoctor());
        Clinic clinic = clinicService.getClinicById(form.getClinic());
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        scheduleService.createSchedule(form.getHour(), form.getDay(), doctorClinic);

        final ModelAndView mav = new ModelAndView("addedSchedule");

        return mav;
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), new Location(form.getLocation()));

        final ModelAndView mav = new ModelAndView("addedClinic");

        return mav;
    }

    @RequestMapping(value = "/addedLocation", method = { RequestMethod.POST })
    public ModelAndView addedLocation(@Valid @ModelAttribute("locationForm") final LocationForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addLocation(form);

        final Location location = locationService.createLocation(form.getName());

        // this could show something more specific, for example, the recently added location.
        // same goes for doctors and clinics and everything you can add
        final ModelAndView mav = new ModelAndView("addedLocation");

        return mav;
    }

    @RequestMapping(value = "/addedSpecialty", method = { RequestMethod.POST })
    public ModelAndView addedSpecialty(@Valid @ModelAttribute("specialtyForm") final SpecialtyForm form, final BindingResult errors){

        if(errors.hasErrors())
            return addSpecialty(form);

        final Specialty specialty = specialtyService.createSpecialty(form.getName());

        final ModelAndView mav = new ModelAndView("addedSpecialty");
        return mav;
    }
}
