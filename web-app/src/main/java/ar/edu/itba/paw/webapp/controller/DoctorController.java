package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.DoctorClinicForm;
import ar.edu.itba.paw.webapp.form.ScheduleForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
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
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorHourService doctorHourService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelAndViewModifier viewModifier;

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public ModelAndView doctorProfile() {
        final ModelAndView mav = new ModelAndView("doctor/doctorProfile");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        List<DoctorClinic> docClis = doctorClinicService.getDoctorClinicsForDoctor(doctor);
        mav.addObject("doctorClinics", docClis);


        List<Appointment> appointments = appointmentService.getAllDoctorsAppointments(doctor);

        mav.addObject("appointments",appointments);
        return mav;
    }

    @RequestMapping(value = "/editProfile", method = { RequestMethod.GET })
    public ModelAndView editProfile() {

        final ModelAndView mav = new ModelAndView("/doctor/editProfile");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        Image image = imageService.getProfileImage(doctor);

        mav.addObject("user", user);
        mav.addObject("doctor", doctor);
        mav.addObject("image", image);

        return mav;
    }

    @RequestMapping(value = "/uploadPicture", method = { RequestMethod.GET })
    public ModelAndView uploadPicture() {
        final ModelAndView mav = new ModelAndView("doctor/uploadPicture");
        return mav;
    }

    @RequestMapping(value = "/addDoctorClinic", method = { RequestMethod.GET })
    public ModelAndView addDoctorClinic(@ModelAttribute("doctorClinicForm") final DoctorClinicForm form){
        final ModelAndView mav = new ModelAndView("doctor/addDoctorClinic");

        viewModifier.addClinics(mav);
        viewModifier.addDoctors(mav);

        return mav;
    }

    @RequestMapping(value ="/addSchedule", method = { RequestMethod.GET })
    public ModelAndView addSchedule(){
        final ModelAndView mav = new ModelAndView("doctor/addSchedule");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        viewModifier.addDoctorClinicsForDoctor(mav, doctor);

        return mav;
    }

    @RequestMapping(value = "/addSchedule/{clinicid}/{license}", method = {RequestMethod.GET})
    public ModelAndView addDoctorShedule(@PathVariable(value = "clinicid") int clinic,
                                         @PathVariable(value = "license") String license,
                                         @ModelAttribute("scheduleForm") final ScheduleForm form){

        final ModelAndView mav = new ModelAndView("doctor/doctorSchedule");
        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, cli);
        viewModifier.addDaysAdnTimes(mav);
        mav.addObject("doctorClinic", doctorClinic);
        return mav;

    }

    @RequestMapping(value = "/addedDoctorClinic", method = { RequestMethod.POST })
    public ModelAndView addedDoctorClinic(@Valid @ModelAttribute("doctorClinicForm") final DoctorClinicForm form,
                                          final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorClinic(form);

        doctorClinicService.createDoctorClinic(doctorService.getDoctorByLicense(form.getDoctor()),
                clinicService.getClinicById(form.getClinic()),
                form.getConsultPrice());

        final ModelAndView mav = new ModelAndView("doctor/addedDoctorClinic");

        return mav;
    }

    @RequestMapping(value = "/addedSchedule/{clinicid}/{license}", method = {RequestMethod.POST})
    public ModelAndView addedSchedule(@PathVariable(value = "clinicid") int clinic,
                                      @PathVariable(value = "license") String license,
                                      @Valid @ModelAttribute("scheduleForm") final ScheduleForm form,
                                      final BindingResult errors){

        if(errors.hasErrors())
            return addDoctorShedule(clinic, license, form);
        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, cli);

        scheduleService.createSchedule(form.getHour(), form.getDay(), doctorClinic);

        final ModelAndView mav = new ModelAndView("doctor/addedSchedule");

        return mav;
    }

    @RequestMapping(value = "/clinics/{clinicid}/{week}", method = {RequestMethod.GET})
    public ModelAndView doctorClinics(@PathVariable(value = "clinicid") int clinic, @PathVariable(value = "week") int week){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, cli);

        List<List<DoctorHour>> doctorsWeek = doctorHourService.getDoctorsWeek(doctorClinic, week);

        final ModelAndView mav = new ModelAndView("doctor/clinicPage");


        viewModifier.addCurrentDates(mav, week);

        mav.addObject("week", doctorsWeek);
        mav.addObject("weekNum", week);
        mav.addObject("doctorClinic", doctorClinic);

        return mav;

    }
}
