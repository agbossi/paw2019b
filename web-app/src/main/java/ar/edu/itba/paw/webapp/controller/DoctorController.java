package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.DoctorClinicForm;
import ar.edu.itba.paw.webapp.form.EditDoctorProfileForm;
import ar.edu.itba.paw.webapp.helpers.ControllerHelper;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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
    private SpecialtyService specialtyService;

    @Autowired
    private DoctorHourService doctorHourService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ModelAndViewModifier viewModifier;

    @Autowired
    private ValidationHelper validator;


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

    @RequestMapping(value = "/editProfileForm", method = { RequestMethod.GET })
    public ModelAndView updateProfile(@ModelAttribute("editProfileForm") final EditDoctorProfileForm form){

        final ModelAndView mav = new ModelAndView("/doctor/editProfileForm");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        Image image = imageService.getProfileImage(doctor);

        List<Specialty> specialties = specialtyService.getSpecialties();

        mav.addObject("user", user);
        mav.addObject("doctor", doctor);
        mav.addObject("image", image);
        mav.addObject("specialties", specialties);

        return mav;
    }



    @RequestMapping(value = "/editProfileFormPost", method = { RequestMethod.POST })
    public ModelAndView updateProfile(@ModelAttribute("editProfileForm") final EditDoctorProfileForm form,
                                      final BindingResult errors,
                                      @RequestParam("photo") MultipartFile photo){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        if(errors.hasErrors()) {
            return updateProfile(form);
        }

        long result = ControllerHelper.updateUserInformation(form.getFirstName(), form.getLastName(), form.getEmail(),
                                                form.getOldPassword(), form.getNewPassword(), form.getRepeatNewPassword());
        result = ControllerHelper.updateDoctorInformation(form.getLicense(), form.getSpecialty(), form.getPhoneNumber());
        result = ControllerHelper.updateProfilePicture(photo);

        // TODO: if any of these results fails what do we do???

        final ModelAndView mav = new ModelAndView("/doctor/editProfile");

        Image image = imageService.getProfileImage(doctor);

        mav.addObject("user", user);
        mav.addObject("doctor", doctor);
        mav.addObject("image", image);

        return mav;
    }

    @RequestMapping(value = "/addDoctorClinic", method = { RequestMethod.GET })
    public ModelAndView addDoctorClinic(@ModelAttribute("doctorClinicForm") final DoctorClinicForm form){
        final ModelAndView mav = new ModelAndView("doctor/addDoctorClinic");

        viewModifier.addClinics(mav);
        viewModifier.addDoctors(mav);

        return mav;
    }

    @RequestMapping(value = "/addSchedule/{clinicid}", method = {RequestMethod.GET})
    public ModelAndView addDoctorSchedule(@PathVariable(value = "clinicid") int clinic){

        final ModelAndView mav = new ModelAndView("doctor/doctorSchedule");

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, cli);
        viewModifier.addDaysAdnTimes(mav);

        List<List<DoctorHour>> doctorsWeek = doctorHourService.getDoctorsWeek(doctorClinic, 2);

        mav.addObject("doctorClinic", doctorClinic);
        mav.addObject("schedule", doctorsWeek);
        return mav;

    }

    @RequestMapping(value = "/addedDoctorClinic", method = { RequestMethod.POST })
    public ModelAndView addedDoctorClinic(@Valid @ModelAttribute("doctorClinicForm") final DoctorClinicForm form,
                                          final BindingResult errors, Locale locale){

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);

        validator.doctorClinicValidate(doctor.getLicense(),form.getClinic(),errors,locale);
        if(errors.hasErrors())
            return addDoctorClinic(form);


        DoctorClinic doctorClinic = doctorClinicService.createDoctorClinic(doctor,
                                               clinicService.getClinicById(form.getClinic()),
                                               form.getConsultPrice());

        final ModelAndView mav = new ModelAndView("doctor/addedDoctorClinic");
        mav.addObject("doctorClinic",doctorClinic );

        return mav;
    }

    @RequestMapping(value = "/addSchedule/{clinicId}/{day}-{hour}", method = {RequestMethod.GET})
    public ModelAndView addedSchedule(@PathVariable(value = "clinicId") int clinic,
                                      @PathVariable(value = "day") int day,
                                      @PathVariable(value = "hour") int hour){

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);

        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, cli);

        if(validator.scheduleValidate(doctorClinic,day,hour)){
            scheduleService.createSchedule(hour, day, doctorClinic);
        }

        final ModelAndView mav = new ModelAndView("redirect:/doctor/addSchedule/" + clinic);

        return mav;
    }

    @RequestMapping(value = "/removeSchedule/{clinicid}/{day}-{hour}", method = {RequestMethod.GET})
    public ModelAndView removeSchedule(@PathVariable(value = "clinicid") int clinic,
                                       @PathVariable(value = "day") int day,
                                       @PathVariable(value = "hour") int hour){

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);

        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, cli);

        if(!validator.scheduleValidate(doctorClinic,day,hour)){
            scheduleService.deleteSchedule(hour, day, doctorClinic);
        }

        final ModelAndView mav = new ModelAndView("redirect:/doctor/addSchedule/" + clinic);

        return mav;
    }

    @RequestMapping(value = "/clinics/{clinicid}/{week}", method = {RequestMethod.GET})
    public ModelAndView doctorClinics(@PathVariable(value = "clinicid") int clinic, @PathVariable(value = "week") int week){

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);

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
