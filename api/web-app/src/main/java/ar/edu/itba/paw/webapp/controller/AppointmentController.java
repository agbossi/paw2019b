package ar.edu.itba.paw.webapp.controller;
/*
import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Locale;

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
    private UserService userService;


    @RequestMapping(value = "/createApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView makeAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                        @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                        @PathVariable(value = "month") int month, @PathVariable(value = "time") int time,
                                        Locale locale){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        appointmentService.createAppointment(license, clinicId, user.getEmail(), year, month, day, time);

        final ModelAndView mav = new ModelAndView("redirect:/appointments");
        return mav;
    }

    @RequestMapping(value = "/cancelApp/{clinicId}/{doctorId}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView cancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "doctorId") String license,
                                          @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                          @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        appointmentService.cancelAppointment(license, clinicId, user.getEmail(), year, month, day, time,false);

        final ModelAndView mav = new ModelAndView("redirect:/appointments");
        return mav;
    }

    @RequestMapping(value = "/docCancelApp/{clinicId}/{patient}/{week}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorCancelAppointment(@PathVariable(value = "clinicId") int clinicId, @PathVariable(value = "patient") String email,
                                                @PathVariable(value = "week") int week,
                                                @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                                @PathVariable(value = "month") int month, @PathVariable(value = "time") int time,
                                                Locale locale){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());
        appointmentService.cancelAppointment(doctor.getLicense(), clinicId, email, year, month, day, time,true);


        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId + "/" + week);
        return mav;
    }

    @RequestMapping(value = "/doctorApp/{clinicid}/{week}/{year}-{month}-{day}-{time}", method = {RequestMethod.GET})
    public ModelAndView doctorMakesAppointment(@PathVariable(value = "clinicid") int clinicId,
                                               @PathVariable(value = "week") int week,
                                               @PathVariable(value = "day") int day, @PathVariable(value = "year") int year,
                                               @PathVariable(value = "month") int month, @PathVariable(value = "time") int time){

        User user = UserContextHelper.getLoggedUser(SecurityContextHolder.getContext(), userService);
        Doctor doctor = doctorService.getDoctorByEmail(user.getEmail());

        appointmentService.createAppointment(doctor.getLicense(), clinicId, doctor.getEmail(), year, month, day, time);


        final ModelAndView mav = new ModelAndView("redirect:/doctor/clinics/" + clinicId +"/" + week);
        return mav;
    }
}

*/

import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.ClinicService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.caching.AppointmentCaching;
import ar.edu.itba.paw.webapp.dto.AppointmentDto;
import ar.edu.itba.paw.webapp.form.AppointmentForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("appointments")
public class AppointmentController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private AppointmentCaching appointmentCaching;

    @Context
    private UriInfo uriInfo;

    //TODO agregar query params para filtros de tiempo
    @GET
    @Path("{userId}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @PreAuthorize("hasPermission(#email, 'user')")
    public Response getUserAppointments(@PathParam("userId") final String email,
                                        @Context Request request) {
        User user = userService.findUserByEmail(email);
        if(user != null) {
            List<AppointmentDto> appointments = appointmentService.getUserAppointments(user)
                    .stream().map(AppointmentDto::fromAppointment).collect(Collectors.toList());
            return CacheHelper.handleResponse(appointments, appointmentCaching,
                    new GenericEntity<List<AppointmentDto>>(appointments) {}, "appointments",
                    request).build();
            // return Response.ok(new GenericEntity<List<AppointmentDto>>(appointments) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @DELETE
    @Path("{userId}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @PreAuthorize("hasPermission(#email, 'user')")
    public Response cancelAppointment(@PathParam("userId") final String email,
                                      @QueryParam("clinicId") final Integer clinic,
                                      @QueryParam("license") final String license,
                                      @QueryParam("year") final Integer year,
                                      @QueryParam("month") final Integer month,
                                      @QueryParam("day") final Integer day,
                                      @QueryParam("time") final Integer time) {
        appointmentService.cancelUserAppointment(email, license, clinic, year, month, day, time);
        return Response.noContent().build();
    }

    @GET
    @Path("{userId}/{clinicId}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @PreAuthorize("hasPermission(#email, 'user')")
    public Response getUserAppointmentsForClinic(@PathParam("userId") final String email,
                                                 @PathParam("clinicId") final Integer clinicId,
                                                 @Context Request request) {
        User user = userService.findUserByEmail(email);
        Clinic clinic = clinicService.getClinicById(clinicId);
        if(user != null && clinic != null) {
            List<AppointmentDto> appointments = appointmentService.getUserAppointmentsForClinic(user, clinic)
                    .stream().map(AppointmentDto::fromAppointment).collect(Collectors.toList());
            return CacheHelper.handleResponse(appointments, appointmentCaching,
                    new GenericEntity<List<AppointmentDto>>(appointments) {},
                    "appointments", request).build();
            //return Response.ok(new GenericEntity<List<AppointmentDto>>(appointments) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @DELETE
    @Path("{userId}/{clinicId}")
    @PreAuthorize("hasPermission(#email, 'user')")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response cancelAppointmentInClinic(@PathParam("userId") final String email,
                                      @PathParam("clinicId") final Integer clinic,
                                      @QueryParam("license") final String license,
                                      @QueryParam("year") final Integer year,
                                      @QueryParam("month") final Integer month,
                                      @QueryParam("day") final Integer day,
                                      @QueryParam("time") final Integer time) {
        appointmentService.cancelUserAppointment(email, license, clinic, year, month, day, time);
        return Response.noContent().build();
    }


    @POST
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasPermission(#form.patient, 'user')")
    public Response createAppointment(final AppointmentForm form) {
        appointmentService.createAppointment(form.getLicense(), form.getClinic(),
                form.getPatient(), form.getYear(), form.getMonth(), form.getDay(),
                form.getTime());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(form.getPatient())
                .path(String.valueOf(form.getClinic())).build()).build();
    }
}