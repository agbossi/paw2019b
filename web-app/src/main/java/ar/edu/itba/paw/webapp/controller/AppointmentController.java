package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.*;
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
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        patientService.setAppointments(patient);

        appointmentService.createAppointment(doctorClinic, patient, cal);

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
        Patient patient = patientService.getPatientByEmail(user.getEmail());
        patientService.setAppointments(patient);

        appointmentService.cancelAppointment(doctorClinic, patient, cal);

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

        Patient patient = patientService.getPatientByEmail(email);
        patientService.setAppointments(patient);

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doc = doctorService.getDoctorByEmail(user.getEmail());
        Clinic clinic = clinicService.getClinicById(clinicId);
        DoctorClinic docCli = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        appointmentService.cancelAppointment(docCli, patient, cal);

        final ModelAndView mav = new ModelAndView("redirect:/doctor");

        return mav;
    }

}

