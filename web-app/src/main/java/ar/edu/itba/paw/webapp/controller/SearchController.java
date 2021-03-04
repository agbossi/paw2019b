package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.dto.*;
import ar.edu.itba.paw.webapp.form.DoctorForm;
import ar.edu.itba.paw.webapp.form.DoctorProfileImageForm;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("doctors") //TODO estos son endpoints que dan info de los doctores,
                   // quizas habria que mergear con los de doctorController para que queden bajo doctors
public class SearchController {

    //TODO: arreglar dtos

    @Autowired
    private DoctorClinicService doctorClinicService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorHourService doctorHourService;

    @Autowired
    private ImageService imageService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response geAvailableDoctors(
            @QueryParam("page") @DefaultValue("0") final Integer page,
            @QueryParam("location") @DefaultValue("") final String location,
            @QueryParam("specialty") @DefaultValue("") final String specialty,
            @QueryParam("firstName") @DefaultValue("") final String firstName,
            @QueryParam("lastName") @DefaultValue("") final String lastName,
            @QueryParam("consultPrice") @DefaultValue("0") final Integer consultPrice,
            @QueryParam("includeUnavailables") @DefaultValue("false") final Boolean includeUnavailable,
            @QueryParam("prepaid") @DefaultValue("") final String prepaid) {

        List<String> licenses = doctorService.getFilteredLicenses(new Location(location), new Specialty(specialty),
                firstName,lastName, new Prepaid(prepaid), consultPrice, includeUnavailable);
        int maxAvailablePage = doctorService.getMaxAvailableDoctorsPage(licenses);

        List<DoctorDto> doctors = doctorService.getPaginatedDoctors(licenses, page)
                .stream().map(d -> DoctorDto.fromDoctor(d, imageService.getProfileImage(d.getLicense()).getImage())).collect(Collectors.toList());

        return Response.ok(new GenericEntity<List<DoctorDto>>(doctors) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(),"first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxAvailablePage).build(),"last")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(),"next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(),"prev")
                .build();
    }

    @GET
    @Path("/{license}")
    @Produces(value = { MediaType.MULTIPART_FORM_DATA })
    public Response getDoctor(@PathParam("license") final String license) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            byte[] profileImage = imageService.getProfileImage(doctor.getLicense()).getImage();
            DoctorDto dto = DoctorDto.fromDoctor(doctor, profileImage);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response createDoctor(@FormDataParam("doctor") final DoctorForm form,
                                 @BeanParam final DoctorProfileImageForm profileImageForm) {
        return Response.ok().build();
    }
    /* @GET
    @Path("/{license}/profile-picture")
    @Produces("image/jpg")
    public Response getDoctorProfilePicture(@PathParam("license") final String license) {
        byte[] image = imageService.getProfileImage(license).getImage();
        //TODO ver como es esto
        final CacheControl cache = new CacheControl();
        cache.setNoTransform(false);
        cache.setMaxAge(31536000);
        return Response.ok(image).build();
    } */

    @GET
    @Path("/{license}/doctorsClinics")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getDoctorPage(@PathParam("license") final String license,
                                  @QueryParam("week") @DefaultValue("1") final Integer week) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            final List<DoctorClinicDto> doctorClinics = doctorClinicService.getDoctorClinicsForDoctor(doctor)
                    .stream().map(dc -> DoctorClinicDto.fromDoctorClinic(dc, uriInfo, getDoctorWeek(dc, week)))
                    .collect(Collectors.toList());
            return Response.ok(new GenericEntity<List<DoctorClinicDto>>(doctorClinics) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    //TODO preguntar a xime como era el tema de la semana inicial a mostrar y hasta donde se podia avanzar
    @GET
    @Path("/{license}/doctorsClinics/{clinic}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getDoctorClinic(@PathParam("license") final String license,
                                    @PathParam("clinic") final Integer clinic,
                                    @QueryParam("week") @DefaultValue("1") final Integer week) {
        DoctorClinic dc = doctorClinicService.getDoctorInClinic(license,clinic);
        if(dc != null) {
            List<List<DoctorHourDto>> doctorWeek = getDoctorWeek(dc, week);
            return Response.ok(DoctorClinicDto.fromDoctorClinic(dc, uriInfo, doctorWeek))
                    .link(uriInfo.getAbsolutePathBuilder().queryParam("week", week - 1).build(),"prev")
                    .link(uriInfo.getAbsolutePathBuilder().queryParam("week", week + 1).build(),"prev")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @GET
    @Path("/{license}/doctorsClinics/{clinic}/appointments")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getDoctorClinicAppointments(@PathParam("license") final String license,
                                                @PathParam("clinic") final Integer clinic) {
        DoctorClinic dc = doctorClinicService.getDoctorInClinic(license,clinic);
        if(dc != null) {
            List<AppointmentDto> appointments = dc.getAppointments()
                    .stream().map(AppointmentDto::fromAppointment).collect(Collectors.toList());
            return Response.ok(new GenericEntity<List<AppointmentDto>>(appointments) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @GET
    @Path("/{license}/doctorsClinics/{clinic}/schedules")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getDoctorClinicSchedules(@PathParam("license") final String license,
                                                @PathParam("clinic") final Integer clinic) {
        DoctorClinic dc = doctorClinicService.getDoctorInClinic(license,clinic);
        if(dc != null) {
            List<ScheduleDto> schedules = dc.getSchedule()
                    .stream().map(ScheduleDto::fromSchedule).collect(Collectors.toList());
            return Response.ok(new GenericEntity<List<ScheduleDto>>(schedules) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }



    // private methods

    private List<List<DoctorHourDto>> getDoctorWeek(DoctorClinic dc, int week) {
        List<List<DoctorHour>> doctorHours = doctorHourService.getDoctorsWeek(dc, week);
        return doctorHours.stream().map(l ->
                l.stream().map(DoctorHourDto::fromDoctorHour).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    //TODO: decidir si hago un dto de favorite + doctorClinic o dejo favorite como endpoint aparte en
    // patient controller y luego en front hago un contains o algo asi (es para que al cargar pagina del
    // doctor, aparezca como favorito). A su vez esta forma requiere Spring Security que no me queda claro
    // si lo podemos usar de esta forma
    /*private boolean isFavorite(Doctor doctor) {
        boolean isFav = false;

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(userEmail);
        if(patient != null) {
            isFav = favoriteService.isFavorite(doctor, patient);
        }
        return isFav;
    } */

}
