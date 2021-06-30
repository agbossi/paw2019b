package ar.edu.itba.paw.webapp.controller;
/*
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
        Image image = imageService.getProfileImage(doctor.getLicense());

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
        Image image = imageService.getProfileImage(doctor.getLicense());

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

        boolean photoError = ValidationHelper.photoValidate(photo);
        if(errors.hasErrors() || photoError) return updateProfile(form,photoError,locale);

        doctorService.updateDoctorProfile(
                user.getEmail(),
                SecurityHelper.processNewPassword(form.getNewPassword(), passwordEncoder),
                form.getFirstName(),form.getLastName(),
                form.getPhoneNumber(),form.getSpecialty(),
                photo
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
                email,
                form.getClinic(),
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
        scheduleService.createSchedule(hour, day, email, clinic);

        final ModelAndView mav = new ModelAndView("redirect:/doctor/addSchedule/" + clinic);
        return mav;
    }

    @RequestMapping(value = "/removeSchedule/{clinicId}/{day}-{hour}", method = {RequestMethod.GET})
    public ModelAndView removeSchedule(@PathVariable(value = "clinicId") int clinic,
                                       @PathVariable(value = "day") int day,
                                       @PathVariable(value = "hour") int hour){

        String email = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        scheduleService.deleteSchedule(hour, day, email, clinic);

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

    // Private methods for DoctorController

    private void setEditFormInformation(EditDoctorProfileForm form, User user, Doctor doctor) {
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setSpecialty(doctor.getSpecialty().getSpecialtyName());
        form.setPhoneNumber(doctor.getPhoneNumber());
    }
}
*/

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.dto.*;
import ar.edu.itba.paw.webapp.form.*;
import ar.edu.itba.paw.webapp.helpers.SecurityHelper;
import ar.edu.itba.paw.webapp.helpers.UserContextHelper;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("doctors") //TODO estos son endpoints que dan info de los doctores,
// quizas habria que mergear con los de doctorController para que queden bajo doctors
public class DoctorController {

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

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppointmentService appointmentService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response geAvailableDoctors(
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("location") @DefaultValue("") final String location,
            @QueryParam("specialty") @DefaultValue("") final String specialty,
            @QueryParam("firstName") @DefaultValue("") final String firstName,
            @QueryParam("lastName") @DefaultValue("") final String lastName,
            @QueryParam("consultPrice") @DefaultValue("0") final Integer consultPrice,
            @QueryParam("includeUnavailables") @DefaultValue("false") final Boolean includeUnavailable,
            @QueryParam("prepaid") @DefaultValue("") final String prepaid) {

        page = (page < 0) ? 0 : page;

        List<String> licenses = doctorService.getFilteredLicenses(new Location(location), new Specialty(specialty),
                firstName,lastName, new Prepaid(prepaid), consultPrice, includeUnavailable);
        int maxAvailablePage = doctorService.getMaxAvailableDoctorsPage(licenses);

        List<DoctorDto> doctors = doctorService.getPaginatedDoctors(licenses, page)
                .stream().map(d -> DoctorDto.fromDoctor(d, uriInfo)).collect(Collectors.toList());

        return Response.ok(new GenericEntity<List<DoctorDto>>(doctors) {})
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", 0).build(),"first")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", maxAvailablePage).build(),"last")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page + 1).build(),"next")
                .link(uriInfo.getAbsolutePathBuilder().queryParam("page", page - 1).build(),"prev")
                .build();
    }

    @GET
    @Path("/{license}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getDoctor(@PathParam("license") final String license) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            DoctorDto dto = DoctorDto.fromDoctor(doctor, uriInfo);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @DELETE
    @Path("/{license}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteDoctor(@PathParam("license") final String license) {
        doctorService.deleteDoctor(license);
        return Response.noContent().build();
    }


    // TODO: como se que el put viene del usuario?
    @PUT
    @Path("/{license}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("license") final String license, EditDoctorProfileForm form) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            doctorService.updateDoctorProfile(
                    doctor.getEmail(),
                    SecurityHelper.processNewPassword(form.getNewPassword(), passwordEncoder),
                    form.getFirstName(),form.getLastName(),
                    form.getPhoneNumber(),form.getSpecialty());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Produces(value = { MediaType.APPLICATION_JSON, })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoctor(final DoctorForm form) {
        doctorService.createDoctor(new Specialty(form.getSpecialty()), form.getLicense(), form.getPhoneNumber()
                ,form.getFirstName(), form.getLastName(), form.getPassword(), form.getEmail());
        return Response.created(uriInfo.getAbsolutePathBuilder().path(form.getLicense()).build()).build();
    }

    @GET
    @Path("/{license}/profileImage")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getProfileImage(@PathParam("license") final String license) {
        Doctor d = doctorService.getDoctorByLicense(license);
        if(d != null) {
            byte[] img = imageService.getProfileImage(d.getLicense()).getImage();
            ImageDto dto = ImageDto.fromImage(img);
            return Response.ok(dto).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @DELETE
    @Path("/{license}/profileImage")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteProfileImage(@PathParam("license") final String license) {
        imageService.deleteProfileImage(license);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{license}/profileImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response updateProfileImage(@PathParam("license") final String license,
                                       @BeanParam final DoctorProfileImageForm profileImageForm) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            imageService.updateProfileImage(profileImageForm.getProfilePictureBytes(), doctor);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @POST
    @Path("/{license}/profileImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response uploadProfileImage(@PathParam("license") final String license,
                                       @BeanParam final DoctorProfileImageForm profileImageForm) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            if(imageService.getProfileImage(doctor.getLicense()) != null) {
                imageService.createProfileImage(profileImageForm.getProfilePictureBytes(), doctor);
                return Response.created(uriInfo.getAbsolutePath()).build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @GET
    @Path("/{license}/doctorsClinics")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getDoctorPage(@PathParam("license") final String license,
                                  @QueryParam("week") @DefaultValue("1") final Integer week) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            final List<DoctorClinicDto> doctorClinics = doctorClinicService.getDoctorClinicsForDoctor(doctor)
                    .stream().map(dc -> DoctorClinicDto.fromDoctorClinic(dc, uriInfo,
                            imageService.getProfileImage(dc.getDoctor().getLicense()).getImage(),
                            getDoctorWeek(dc, week))).collect(Collectors.toList());
            return Response.ok(new GenericEntity<List<DoctorClinicDto>>(doctorClinics) {}).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }


    @POST
    @Path("/{license}/doctorsClinics")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response createDoctorClinic(@PathParam("license") final String license,
                                       final DoctorClinicForm form) {
        Doctor doctor = doctorService.getDoctorByLicense(license);
        if(doctor != null) {
            doctorClinicService.createDoctorClinic(doctor.getEmail(), form.getClinic(), form.getConsultPrice());
            return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(form.getClinic())).build()).build();
        }
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @DELETE
    @Path("/{license}/doctorsClinics/{clinic}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteDoctorClinic(@PathParam("license") final String license,
                                       @PathParam("clinic") final Integer clinic) {
        doctorClinicService.deleteDoctorClinic(license, clinic);
        return Response.noContent().build();
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
            return Response.ok(DoctorClinicDto.fromDoctorClinic(dc, uriInfo,
                    imageService.getProfileImage(dc.getDoctor().getLicense()).getImage() , doctorWeek))
                    .link(uriInfo.getAbsolutePathBuilder().queryParam("week", week - 1).build(),"prev")
                    .link(uriInfo.getAbsolutePathBuilder().queryParam("week", week + 1).build(),"next")
                    .build();
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

    @DELETE
    @Path("/{license}/doctorsClinics/{clinic}/schedules")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteDoctorClinicSchedule(@PathParam("license") final String license,
                                               @PathParam("clinic") final Integer clinic,
                                               @QueryParam("day") final Integer day,
                                               @QueryParam("hour") final Integer hour) {
        scheduleService.deleteSchedule(hour, day, license, clinic);
        return Response.noContent().build();
    }

    //TODO el mismo tema de quien hace este post
    @POST
    @Path("/{license}/doctorsClinics/{clinic}/schedules")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSchedule(@PathParam("license") final String license,
                                   @PathParam("clinic") final Integer clinic,
                                   ScheduleForm form) {
        DoctorClinic dc = doctorClinicService.getDoctorInClinic(license,clinic);
            scheduleService.createSchedule(form.getHour(), form.getDay(),
                    dc.getDoctor().getEmail(), dc.getClinic().getId());
        return Response.created(uriInfo.getAbsolutePath()).build(); //TODO esto no me cierra del todo
    }


    //TODO repite el mismo appointment una y otra vez
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

    //TODO probar cuando la linea de userEmail haga sentido. Creo que no se puede usar el context holder porque rompe con rest
    @DELETE
    @Path("/{license}/doctorsClinics/{clinic}/appointments")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response deleteDoctorClinicAppointment(@PathParam("license") final String license,
                                                  @PathParam("clinic") final Integer clinic,
                                                  @QueryParam("year") final Integer year,
                                                  @QueryParam("month") final Integer month,
                                                  @QueryParam("day") final Integer day,
                                                  @QueryParam("time") final Integer time) {

        String userEmail = UserContextHelper.getLoggedUserEmail(SecurityContextHolder.getContext());
        appointmentService.cancelAppointment(license, clinic, userEmail,
                year, month, day, time, doctorService.isDoctor(userEmail));
        return Response.noContent().build();
    }


    @POST
    @Path("/{license}/doctorsClinics/{clinic}/appointments")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAppointment(@PathParam("license") final String license,
                                      @PathParam("clinic") final Integer clinic,
                                      AppointmentForm form) {
        appointmentService.createAppointment(form.getLicense(), form.getClinic(), form.getPatient(),
                form.getYear(), form.getMonth(), form.getDay(), form.getTime());
        return Response.created(uriInfo.getAbsolutePath()).build(); //TODO esto no me cierra del todo
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
