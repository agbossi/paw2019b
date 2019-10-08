package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.DoctorClinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Calendar;
import java.util.Locale;

@Component
public class ValidationHelper {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PrepaidService prepaidService;

    @Autowired
    PrepaidToClinicService prepaidToClinicService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    LocationService locationService;

    @Autowired
    SpecialtyService specialtyService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorClinicService doctorClinicService;

    public void validateSpecialty(String name,BindingResult errors,Locale locale){
        if(specialtyService.getSpecialtyByName(name) != null){
            FieldError specialtyExists = new FieldError("form","name",messageSource.getMessage("specialty.already.exists",null,locale));
            errors.addError(specialtyExists);
        }
    }

    public void validateLocation(String location,BindingResult errors,Locale locale){
        if(locationService.getLocationByName(location) != null){
            FieldError locationExists = new FieldError("form","name",messageSource.getMessage("location.already.exists",null,locale));
            errors.addError(locationExists);
        }
    }

    public void clinicValidate(String name,String address,String location,BindingResult errors,Locale locale){
        if(clinicService.clinicExists(name,address,location)){
            FieldError clinicExists = new FieldError("form","location",messageSource.getMessage("clinic.already.exists",null,locale));
            errors.addError(clinicExists);
        }
    }

    public void prepaidToClinicValidate(String prepaid,int clinic,BindingResult errors,Locale locale){
        if(prepaidToClinicService.clinicHasPrepaid(prepaid,clinic)){
            FieldError clinicHasPrepaidError = new FieldError("form","prepaid",messageSource.getMessage("prepaid.already.exists",null,locale));
            errors.addError(clinicHasPrepaidError);
        }
    }

    public void prepaidValidate(final String name,BindingResult errors,Locale locale){
        if(prepaidService.getPrepaidByName(name) != null){
            FieldError prepaidExistsError = new FieldError("form","name",messageSource.getMessage("prepaid.already.exists",null,locale));
            errors.addError(prepaidExistsError);
        }
    }

    public void signUpValidate(final String password,final String repeatPassword,final String email,final BindingResult errors, Locale locale){

        passwordValidate(password,repeatPassword,errors,locale);

        if (userService.userExists(email)) {
            FieldError ssoError = new FieldError("form", "email",messageSource.getMessage("user.exist.error.message",null,locale));
            errors.addError(ssoError);
        }
    }
    public void passwordValidate(final String password,final String repeatPassword,final BindingResult errors,Locale locale){
        if(!password.equals(repeatPassword)){
            FieldError passwordNotMatchingError = new FieldError("form","repeatPassword",messageSource.getMessage("user.password.not.matching",null,locale));
            errors.addError(passwordNotMatchingError);
        }
    }
    public void licenseValidate(String license,BindingResult errors,Locale locale){
        if(doctorService.getDoctorByLicense(license) != null){
            FieldError licenseExistsError = new FieldError("form","license",messageSource.getMessage("doctor.license.already.exists",null,locale));
            errors.addError(licenseExistsError);
        }
    }
    public boolean scheduleValidate(DoctorClinic doctorClinic, int day, int hour){
        return scheduleService.hasSchedule(doctorClinic,day,hour);
    }
    public boolean appointmentValidate(DoctorClinic doctorClinic, Calendar date){
        return appointmentService.hasAppointment(doctorClinic,date) != null;
    }
    public void doctorClinicValidate(String license,int clinic,BindingResult errors,Locale locale){
        if(doctorClinicService.getDoctorInClinic(license,clinic) != null){
            FieldError doctorExistsError = new FieldError("form","clinic",messageSource.getMessage("doctor.already.in.clinic",null,locale));
            errors.addError(doctorExistsError);
        }
    }
}
