package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.model.exceptions.*;
import ar.edu.itba.paw.webapp.caching.AppointmentCaching;
import ar.edu.itba.paw.webapp.dto.AppointmentDto;
import ar.edu.itba.paw.webapp.form.AppointmentForm;
import ar.edu.itba.paw.webapp.helpers.CacheHelper;
import ar.edu.itba.paw.webapp.helpers.PaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AppointmentCaching appointmentCaching;

    @Autowired
    private DoctorService doctorService;

    @Context
    private UriInfo uriInfo;

    /**
     * Returns a paginated list of a users appointments (doctor or patient)
     * @param email
     * @return List of Appointment
     * @throws EntityNotFoundException
     */
    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    //@PreAuthorize("hasPermission(#email, 'email')")
    public Response getUserAppointments(@QueryParam("email") final String email,
                                        @QueryParam("page") @DefaultValue("0") Integer page,
                                        @QueryParam("mode") @DefaultValue("taken") String mode,
                                        @Context Request request) throws EntityNotFoundException {
        page = (page < 0) ? 0 : page;

        User user = userService.findUserByEmail(email);
        if(user == null) throw new EntityNotFoundException("user");
        if(mode.equals("available")) {
            Doctor doc = doctorService.getDoctorByEmail(email);
            if (doc == null) throw new EntityNotFoundException("doctor");
            List<AppointmentDto> appointments = appointmentService.getDoctorsAvailableAppointments(doc)
                    .stream().map(appointment -> AppointmentDto.fromAppointment(appointment, uriInfo))
                    .collect(Collectors.toList());
            return CacheHelper.handleResponse(appointments, appointmentCaching,
                    new GenericEntity<List<AppointmentDto>>(appointments) {}, "appointments",
                    request).build();
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(!auth.getName().equals(email)) throw new AccessDeniedException("");
            List<AppointmentDto> appointments = appointmentService.getPaginatedAppointments(user, page)
                    .stream().map(appointment -> AppointmentDto.fromAppointment(appointment, uriInfo))
                    .collect(Collectors.toList());

            int maxPage = appointmentService.getMaxAvailablePage(user) - 1;

            return PaginationHelper.handlePagination(page, maxPage, "appointments",
                    uriInfo, appointments, appointmentCaching,
                    new GenericEntity<List<AppointmentDto>>(appointments) {}, request);
        }
    }

    /**
     * For doctor or user to cancel an appointment
     * @param email
     * @param clinic
     * @param license
     * @param year
     * @param month
     * @param day
     * @param time
     * @return
     * @throws EntityNotFoundException
     * @throws RequestEntityNotFoundException
     */
    @DELETE
    @Produces(value = { MediaType.APPLICATION_JSON })
    //@PreAuthorize("hasPermission(#email, 'email')")
    public Response cancelAppointment(@QueryParam("email") final String email,
                                      @QueryParam("clinic") final Integer clinic,
                                      @QueryParam("license") final String license,
                                      @QueryParam("year") final Integer year,
                                      @QueryParam("month") final Integer month,
                                      @QueryParam("day") final Integer day,
                                      @QueryParam("time") final Integer time) throws EntityNotFoundException,
            RequestEntityNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getName().equals(email)) throw new AccessDeniedException("");
        appointmentService.cancelUserAppointment(email, license, clinic, year, month, day, time);
        return Response.noContent().build();


    }


    /**
     * For USER to make an appointment
     * @param form
     * @return
     */
    @POST
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasPermission(#form.patient, 'user')")
    public Response createAppointment(final AppointmentForm form) throws
            DateInPastException, OutOfScheduleException, AppointmentAlreadyScheduledException, HasAppointmentException {
        appointmentService.createAppointment(form.getLicense(), form.getClinic(),
                form.getPatient(), form.getYear(), form.getMonth(), form.getDay(),
                form.getTime());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(form.getPatient())
                .build()).build();
    }
}