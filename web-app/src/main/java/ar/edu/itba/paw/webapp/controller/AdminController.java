package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.webapp.form.*;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import ar.edu.itba.paw.webapp.helpers.ViewModifierHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    private PrepaidToClinicService prepaidToClinicService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/doctors", method = { RequestMethod.GET })
    public ModelAndView doctors(){
        final ModelAndView mav = new ModelAndView("admin/doctors");
        ViewModifierHelper.addDoctors(mav, doctorService);
        return mav;
    }
    
    @RequestMapping(value = "/addDoctor", method = { RequestMethod.GET })
    public ModelAndView addDoctor(@ModelAttribute("doctorForm") final DoctorForm form){
        final ModelAndView mav = new ModelAndView("admin/addDoctor");

        ViewModifierHelper.addSearchInfo(mav, locationService, specialtyService, clinicService, prepaidService);


        return mav;
    }

    @RequestMapping(value = "/deleteDoctor/{license}", method = { RequestMethod.GET })
    public ModelAndView deleteDoctor(@PathVariable(value = "license") String license){
        doctorService.deleteDoctor(license);
        return doctors();
    }

    @RequestMapping(value = "/addedDoctor", method = { RequestMethod.POST })
    public ModelAndView addedDoctor(@Valid @ModelAttribute("doctorForm") final DoctorForm form, final BindingResult errors) {


        if (errors.hasErrors())
            return addDoctor(form);

        String encodedPassword = passwordEncoder.encode(form.getPassword());
       User user = userService.createUser(form.getFirstName(),form.getLastName(),encodedPassword,form.getEmail());
        Doctor doctor = doctorService.createDoctor(new Specialty(form.getSpecialty()),
                form.getLicense(),
                form.getPhoneNumber(),
                user
        );


        final ModelAndView mav = new ModelAndView("admin/addedDoctor");
        mav.addObject("doctor", doctor);

        return mav;
    }

    @RequestMapping(value = "/clinics", method = { RequestMethod.GET })
    public ModelAndView clinics(){
        final ModelAndView mav = new ModelAndView("admin/clinics");
        ViewModifierHelper.addClinics(mav, clinicService);

        return mav;
    }

    @RequestMapping(value = "/addClinic", method = { RequestMethod.GET })
    public ModelAndView addClinic(@ModelAttribute("clinicForm") final ClinicForm form){
        final ModelAndView mav = new ModelAndView("admin/addClinic");
        ViewModifierHelper.addLocations(mav, locationService);

        return mav;
    }

    @RequestMapping(value = "/editClinic/{id}", method = { RequestMethod.GET})
    public ModelAndView editClinic(@ModelAttribute("clinicForm") final ClinicForm form,
                                   @PathVariable(value = "id") int id){
        Clinic clinic = clinicService.getClinicById(id);
        form.setName(clinic.getName());
        form.setAddress(clinic.getAddress());
        form.setLocation(clinic.getLocation().getLocationName());
        final ModelAndView mav = new ModelAndView("admin/editClinic");
        mav.addObject("clinic", clinic);
        ViewModifierHelper.addLocations(mav, locationService);
        return mav;
    }

    @RequestMapping(value = "/editClinic/{id}/post", method = { RequestMethod.POST})
    public ModelAndView editClinicPost(@Valid @ModelAttribute("clinicForm") final ClinicForm form,
                                        final BindingResult errors, @PathVariable(value = "id") int id){
        if(errors.hasErrors())
            return editClinic(form, id);

        clinicService.updateClinic(id, form.getName(), form.getAddress(), form.getLocation());
        return clinics();
    }

    @RequestMapping(value = "/deleteClinic/{id}", method = { RequestMethod.GET })
    public ModelAndView deleteClinic(@PathVariable(value = "id") int id){
        clinicService.deleteClinic(id);
        return clinics();
    }

    @RequestMapping(value = "/addedClinic", method = { RequestMethod.POST })
    public ModelAndView addedClinic(@Valid @ModelAttribute("clinicForm") final ClinicForm form, final BindingResult errors,Locale locale){

        if(errors.hasErrors())
            return addClinic(form);

        final Clinic clinic = clinicService.createClinic(form.getName(), form.getAddress(), new Location(form.getLocation()));

        final ModelAndView mav = new ModelAndView("admin/addedClinic");
        mav.addObject("clinic", clinic);

        return mav;
    }

    @RequestMapping(value = "/locations", method = { RequestMethod.GET })
    public ModelAndView locations(){
        final ModelAndView mav = new ModelAndView("admin/locations");
        ViewModifierHelper.addLocations(mav, locationService);
        return mav;
    }

    @RequestMapping(value = "/addLocation", method = { RequestMethod.GET })
    public ModelAndView addLocation(@ModelAttribute("locationForm") final LocationForm form){
        final ModelAndView mav = new ModelAndView("admin/addLocation");
        return mav;
    }

    @RequestMapping(value = "/editLocation/{locationName}", method = { RequestMethod.GET})
    public ModelAndView editLocation(@ModelAttribute("locationForm") final LocationForm form,
                                     @PathVariable(value = "locationName") String name){
        form.setName(name);
        final ModelAndView mav = new ModelAndView("admin/editLocation");
        mav.addObject("location", locationService.getLocationByName(name));
        return mav;
    }

    @RequestMapping(value = "/editLocation/{locationName}/post", method = { RequestMethod.POST})
    public ModelAndView editLocationPost(@Valid @ModelAttribute("locationForm") final LocationForm form,
                                         final BindingResult errors, @PathVariable(value = "locationName") String name){
        if(errors.hasErrors())
            return editLocation(form, name);

        locationService.updateLocation(name, form.getName());
        return locations();
    }

    @RequestMapping(value = "/deleteLocation/{locationName}", method = { RequestMethod.GET })
    public ModelAndView deleteLocation(@PathVariable(value = "locationName") String name){
        locationService.deleteLocation(name);
        return locations();
    }

    @RequestMapping(value = "/addedLocation", method = { RequestMethod.POST })
    public ModelAndView addedLocation(@Valid @ModelAttribute("locationForm") final LocationForm form, final BindingResult errors,Locale locale){

        if(errors.hasErrors())
            return addLocation(form);

        final Location location = locationService.createLocation(form.getName());

        final ModelAndView mav = new ModelAndView("admin/addedLocation");
        mav.addObject("location", location);

        return mav;
    }

    @RequestMapping(value = "/specialties", method = {RequestMethod.GET})
    public ModelAndView specialties(){
        final ModelAndView mav = new ModelAndView("admin/specialties");
        ViewModifierHelper.addSpecialties(mav, specialtyService);
        return mav;
    }

    @RequestMapping(value = "/addSpecialty", method = {RequestMethod.GET})
    public ModelAndView addSpecialty(@ModelAttribute("specialtyForm") final SpecialtyForm form){
        final ModelAndView mav = new ModelAndView("admin/addSpecialty");
        return mav;
    }

    @RequestMapping(value = "/deleteSpecialty/{specialtyName}", method = { RequestMethod.GET })
    public ModelAndView deleteSpecialty(@PathVariable(value = "specialtyName") String name){
        specialtyService.deleteSpecialty(name);
        return specialties();
    }

    @RequestMapping(value = "/editSpecialty/{specialtyName}", method = { RequestMethod.GET})
    public ModelAndView editSpecialty(@ModelAttribute("specialtyForm") final SpecialtyForm form,
                                     @PathVariable(value = "specialtyName") String name){
        form.setName(name);
        final ModelAndView mav = new ModelAndView("admin/editSpecialty");
        mav.addObject("specialty", specialtyService.getSpecialtyByName(name));
        return mav;
    }

    @RequestMapping(value = "/editSpecialty/{specialtyName}/post", method = { RequestMethod.POST})
    public ModelAndView editSpecialtyPost(@Valid @ModelAttribute("specialtyForm") final SpecialtyForm form,
                                         final BindingResult errors, @PathVariable(value = "specialtyName") String name){
        if(errors.hasErrors())
            return editSpecialty(form, name);

        specialtyService.updateSpecialty(name, form.getName());
        return specialties();
    }

    @RequestMapping(value = "/addedSpecialty", method = { RequestMethod.POST })
    public ModelAndView addedSpecialty(@Valid @ModelAttribute("specialtyForm") final SpecialtyForm form, final BindingResult errors,Locale locale){

        if(errors.hasErrors())
            return addSpecialty(form);

        final Specialty specialty = specialtyService.createSpecialty(form.getName());

        final ModelAndView mav = new ModelAndView("admin/addedSpecialty");
        mav.addObject("specialty", specialty);

        return mav;
    }

    @RequestMapping(value = "/prepaids",method = { RequestMethod.GET })
    public ModelAndView prepaids(){
        final ModelAndView mav = new ModelAndView("admin/prepaids");
        ViewModifierHelper.addPrepaids(mav, prepaidService);
        return mav;
    }

    @RequestMapping(value = "/addPrepaid",method = { RequestMethod.GET })
    public ModelAndView addPrepaid(@ModelAttribute("prepaidForm") final PrepaidForm form){
        final ModelAndView mav = new ModelAndView("admin/addPrepaid");
        return mav;
    }

    @RequestMapping(value = "/editPrepaid/{name}", method = { RequestMethod.GET})
    public ModelAndView editPrepaid(@ModelAttribute("prepaidForm") final PrepaidForm form,
                                      @PathVariable(value = "name") String name){
        form.setName(name);
        final ModelAndView mav = new ModelAndView("admin/editPrepaid");
        mav.addObject("prepaid", prepaidService.getPrepaidByName(name));
        return mav;
    }

    @RequestMapping(value = "/editPrepaid/{name}/post", method = { RequestMethod.POST})
    public ModelAndView editPrepaidPost(@Valid @ModelAttribute("prepaidForm") final PrepaidForm form,
                                          final BindingResult errors, @PathVariable(value = "name") String name){
        if(errors.hasErrors())
            return editPrepaid(form, name);

        prepaidService.updatePrepaid(name, form.getName());
        return specialties();
    }

    @RequestMapping(value = "/deletePrepaid/{prepaidName}", method = { RequestMethod.GET })
    public ModelAndView deletePrepaid(@PathVariable(value = "prepaidName") String name){
        prepaidService.deletePrepaid(name);
        return prepaids();
    }

    @RequestMapping(value = "/addedPrepaid",method = { RequestMethod.POST })
    public ModelAndView addedPrepaid(@Valid @ModelAttribute("prepaidForm") final PrepaidForm form,final BindingResult errors,Locale locale){

       // validator.prepaidValidate(form.getName(),errors,locale);

        if (errors.hasErrors())
            return addPrepaid(form);


        Prepaid prepaid = prepaidService.createPrepaid(form.getName());
        final ModelAndView mav = new ModelAndView("admin/addedPrepaid");
        mav.addObject("prepaid", prepaid);

        return mav;
    }

    @RequestMapping(value = "/prepaidClinics", method = { RequestMethod.GET })
    public ModelAndView prepaidClinics() {
        final ModelAndView mav = new ModelAndView("/admin/prepaidClinics");
        ViewModifierHelper.addPrepaidClinics(mav, prepaidToClinicService);
        return mav;
    }

    @RequestMapping(value = "/deletePrepaidClinic/{prepaid}/{clinicId}", method = { RequestMethod.GET })
    public ModelAndView deletePrepaidClinic(@PathVariable(value = "prepaid") String prepaid,
                                            @PathVariable(value = "clinicId") int clinicId) {
        prepaidToClinicService.deletePrepaidFromClinic(prepaid, clinicId);
        return prepaidClinics();
    }

    @RequestMapping(value = "/addPrepaidToClinic",method = { RequestMethod.GET })
    public ModelAndView addPrepaidToClinic(@ModelAttribute("prepaidToClinicForm") final PrepaidToClinicForm form){
        final ModelAndView mav = new ModelAndView("admin/addPrepaidToClinic");

        ViewModifierHelper.addClinics(mav, clinicService);
        ViewModifierHelper.addPrepaids(mav, prepaidService);
        return mav;
    }

    @RequestMapping(value = "/addedPrepaidToClinic",method = { RequestMethod.POST })
    public ModelAndView addedPrepaidToClinic(@Valid @ModelAttribute("prepaidToClinicForm") final PrepaidToClinicForm form,final BindingResult errors,Locale locale){

        //validator.prepaidToClinicValidate(form.getPrepaid(),form.getClinic(),errors,locale);

        if (errors.hasErrors())
            return addPrepaidToClinic(form);

        PrepaidToClinic prepaidToClinic = prepaidToClinicService.addPrepaidToClinic(new Prepaid(form.getPrepaid()),clinicService.getClinicById(form.getClinic()));
        final ModelAndView mav = new ModelAndView("admin/addedPrepaidToClinic");
        mav.addObject("prepaidToClinic", prepaidToClinic);

        return mav;
    }
}
