package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.DoctorClinicForm;
import ar.edu.itba.paw.webapp.form.EditDoctorProfileForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import ar.edu.itba.paw.webapp.helpers.ViewModifierHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
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
    private SpecialtyService specialtyService;

    @Autowired
    private DoctorHourService doctorHourService;

    @Autowired
    private ImageService imageService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public ModelAndView updateProfile(@ModelAttribute("editProfileForm") final EditDoctorProfileForm form,boolean photoError,Locale locale){

        final ModelAndView mav = new ModelAndView("/doctor/editProfileForm");

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        Image image = imageService.getProfileImage(doctor);

        setEditFormInformation(form, user, doctor);

        List<Specialty> specialties = specialtyService.getSpecialties();

        mav.addObject("user", user);
        mav.addObject("doctor", doctor);
        mav.addObject("image", image);
        mav.addObject("specialties", specialties);
        if(photoError){
            mav.addObject("errorMessage", messageSource.getMessage("doctor.photo.not.valid",null,locale));
        }

        return mav;
    }



    @RequestMapping(value = "/editProfileForm", method = { RequestMethod.POST })
    public ModelAndView updateProfile(@Valid @ModelAttribute("editProfileForm") final EditDoctorProfileForm form,
                                      final BindingResult errors,
                                      @RequestParam("photo") MultipartFile photo,Locale locale){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        boolean photoError = ValidationHelper.photoValidate(photo);

        if(errors.hasErrors() || photoError) {
            return updateProfile(form,photoError,locale);
        }

        String password = null;
        if(!form.getNewPassword().equals("")){
            password = passwordEncoder.encode(form.getNewPassword());
        }

        userService.updateUser(user.getEmail(),password,form.getFirstName(),form.getLastName());
        doctorService.updateDoctor(doctor.getLicense(),form.getPhoneNumber(),form.getSpecialty());
        imageService.updateProfileImage(photo,doctor);

        return editProfile();
    }

    @RequestMapping(value = "/addDoctorClinic", method = { RequestMethod.GET })
    public ModelAndView addDoctorClinic(@ModelAttribute("doctorClinicForm") final DoctorClinicForm form){
        final ModelAndView mav = new ModelAndView("doctor/addDoctorClinic");

        mav.addObject("clinics", clinicService.getClinics());
        mav.addObject("doctors", doctorService.getDoctors());

        return mav;
    }

    @RequestMapping(value = "/deleteDoctorClinic/{license}/{clinicid}", method = { RequestMethod.GET })
    public ModelAndView deleteDoctorClinic(@PathVariable(value = "license") String license,
                                           @PathVariable(value = "clinicid") int clinic){

        doctorClinicService.deleteDoctorClinic(license, clinic);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/addSchedule/{clinicid}", method = {RequestMethod.GET})
    public ModelAndView addDoctorSchedule(@PathVariable(value = "clinicid") int clinic){

        final ModelAndView mav = new ModelAndView("doctor/doctorSchedule");

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);
        Clinic cli = clinicService.getClinicById(clinic);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doctor, cli);

        mav.addObject("days", ViewModifierHelper.getDays());
        mav.addObject("times", ViewModifierHelper.getTimes());

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

        if(!ValidationHelper.scheduleValidate(doctorClinic.getDoctor(),day,hour,scheduleService)){
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

        if(ValidationHelper.scheduleValidate(doctorClinic.getDoctor(),day,hour,scheduleService)){
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

        List<Calendar> month = ViewModifierHelper.getMonth(week);
        mav.addObject("days", month);
        mav.addObject("today", Calendar.getInstance());
        mav.addObject("week", doctorsWeek);
        mav.addObject("weekNum", week);
        mav.addObject("doctorClinic", doctorClinic);

        return mav;
    }

    // Private methods for DoctorController //

    private void setEditFormInformation(EditDoctorProfileForm form, User user, Doctor doctor) {
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setSpecialty(doctor.getSpecialty().getSpecialtyName());
        form.setPhoneNumber(doctor.getPhoneNumber());
    }
}
