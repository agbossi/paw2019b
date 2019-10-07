package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
public class AppointmentController {

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/createApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView makeAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                        @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                        @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, time, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);

        appointmentService.createAppointment(doctorClinic, user, cal);
        emailService.sendSimpleMail(user.getEmail(),"${appointment.created.subject}","${appointment.created.text}");

        final ModelAndView mav = new ModelAndView("redirect:/appointments");


        return mav;

    }

    @RequestMapping(value = "/cancelApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView cancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                          @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                          @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, time, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Doctor doc = doctorService.getDoctorByLicense(license);
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);


        appointmentService.cancelAppointment(doctorClinic, user, cal);

        final ModelAndView mav = new ModelAndView("redirect:/appointments");

        return mav;
    }

    @RequestMapping(value = "/docCancelApp/{clinicId}/{patient}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorCancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "patient") String email,
                                                @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                                @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, time, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);

        User patient = userService.findUserByEmail(email);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doc = doctorService.getDoctorByEmail(user.getEmail());
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic docCli = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        appointmentService.cancelAppointment(docCli, patient, cal);
        emailService.sendSimpleMail(patient.getEmail(),"${appointment.cancelled.subject}","${appointment.cancelled.text}");

        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId +"/1");

        return mav;
    }

    @RequestMapping(value = "/doctorApp/{clinicid}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorMakesAppointment(@PathVariable(value = "clinicid") int clinicId,
                                               @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                               @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, time, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);


        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doc = doctorService.getDoctorByEmail(user.getEmail());
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic docCli = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        appointmentService.createAppointment(docCli, user, cal);

        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId +"/1");
        return mav;

    }

}

