package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.DoctorClinicForm;
import ar.edu.itba.paw.webapp.form.EditDoctorProfileForm;
import ar.edu.itba.paw.webapp.helpers.SecurityHelper;
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
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if(errors.hasErrors() || photoError) return updateProfile(form,photoError,locale);

        doctorService.updateDoctorProfile(
                user.getEmail(),
                SecurityHelper.processNewPassword(form.getNewPassword(), passwordEncoder),
                form.getFirstName(),form.getLastName(),
                doctor.getLicense(),form.getPhoneNumber(),form.getSpecialty(),
                photo,doctor
        );
        return editProfile();
    }

    @RequestMapping(value = "/addDoctorClinic", method = { RequestMethod.GET })
    public ModelAndView addDoctorClinic(@ModelAttribute("doctorClinicForm") final DoctorClinicForm form){
        final ModelAndView mav = new ModelAndView("doctor/addDoctorClinic");
        mav.addObject("clinics", clinicService.getClinics());
        mav.addObject("doctors", doctorService.getDoctors());
        return mav;
    }

    @RequestMapping(value = "/deleteDoctorClinic/{license}/{clinicId}", method = { RequestMethod.GET })
    public ModelAndView deleteDoctorClinic(@PathVariable(value = "license") String license,
                                           @PathVariable(value = "clinicId") int clinic){
        doctorClinicService.deleteDoctorClinic(license, clinic);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/addSchedule/{clinicId}", method = {RequestMethod.GET})
    public ModelAndView addDoctorSchedule(@PathVariable(value = "clinicId") int clinic){

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(
                doctorService.getDoctorByEmail(userEmail),
                clinicService.getClinicById(clinic));
        List<List<DoctorHour>> doctorsWeek = doctorHourService.getDoctorsWeek(doctorClinic, 2);

        final ModelAndView mav = new ModelAndView("doctor/doctorSchedule");
        mav.addObject("days", ViewModifierHelper.getDays());
        mav.addObject("times", ViewModifierHelper.getTimes());
        mav.addObject("doctorClinic", doctorClinic);
        mav.addObject("schedule", doctorsWeek);
        return mav;
    }

    @RequestMapping(value = "/addedDoctorClinic", method = { RequestMethod.POST })
    public ModelAndView addedDoctorClinic(@Valid @ModelAttribute("doctorClinicForm") final DoctorClinicForm form,
                                          final BindingResult errors, Locale locale){
        if(errors.hasErrors())
            return addDoctorClinic(form);

        String email = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        DoctorClinic doctorClinic = doctorClinicService.createDoctorClinic(
                doctorService.getDoctorByEmail(email),
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

        String email = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(
                doctorService.getDoctorByEmail(email),
                clinicService.getClinicById(clinic));
        scheduleService.createSchedule(hour, day, doctorClinic);

        final ModelAndView mav = new ModelAndView("redirect:/doctor/addSchedule/" + clinic);
        return mav;
    }

    @RequestMapping(value = "/removeSchedule/{clinicId}/{day}-{hour}", method = {RequestMethod.GET})
    public ModelAndView removeSchedule(@PathVariable(value = "clinicId") int clinic,
                                       @PathVariable(value = "day") int day,
                                       @PathVariable(value = "hour") int hour){

        String email = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(
                doctorService.getDoctorByEmail(email),
                clinicService.getClinicById(clinic));
        scheduleService.deleteSchedule(hour, day, doctorClinic);

        final ModelAndView mav = new ModelAndView("redirect:/doctor/addSchedule/" + clinic);
        return mav;
    }

    @RequestMapping(value = "/clinics/{clinicId}/{week}", method = {RequestMethod.GET})
    public ModelAndView doctorClinics(@PathVariable(value = "clinicId") int clinic, @PathVariable(value = "week") int week){

        String email = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(
                doctorService.getDoctorByEmail(email),
                clinicService.getClinicById(clinic));
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

    /* Private methods for DoctorController */

    private void setEditFormInformation(EditDoctorProfileForm form, User user, Doctor doctor) {
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setSpecialty(doctor.getSpecialty().getSpecialtyName());
        form.setPhoneNumber(doctor.getPhoneNumber());
    }
}
