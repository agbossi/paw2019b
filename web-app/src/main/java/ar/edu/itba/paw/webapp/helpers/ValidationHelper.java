package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.DoctorClinic;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ValidationHelper {

    private ValidationHelper() {
        throw new UnsupportedOperationException();
    }

   /* final static int MIN_SIZE = 8;

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

        emailValidate(email,errors,locale);
    }
    public void passwordValidate(final String password,final String repeatPassword,final BindingResult errors,Locale locale){
        if(!password.equals("") && password.length() < MIN_SIZE){
            FieldError passwordNotMatchingError = new FieldError("form","newPassword",messageSource.getMessage("user.password.too.short",null,locale));
            errors.addError(passwordNotMatchingError);
        }
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
    } */
   public static boolean scheduleValidate(Doctor doctor, int day, int hour,ScheduleService scheduleService){
       return scheduleService.doctorHasSchedule(doctor,day,hour);
   }

   public static boolean appointmentValidate(String doctorLicense,String patientEmail,Calendar date,AppointmentService appointmentService){
       return appointmentService.hasAppointment(doctorLicense,patientEmail,date);
   }

   /* public boolean scheduleValidate(Doctor doctor, int day, int hour){
        return scheduleService.doctorHasSchedule(doctor,day,hour);
    }
    public boolean appointmentValidate(String doctorLicense,String patientEmail,Calendar date){
        return appointmentService.hasAppointment(doctorLicense,patientEmail,date);
    }
    public void doctorClinicValidate(String license,int clinic,BindingResult errors,Locale locale){
        if(doctorClinicService.getDoctorInClinic(license,clinic) != null){
            FieldError doctorExistsError = new FieldError("form","clinic",messageSource.getMessage("doctor.already.in.clinic",null,locale));
            errors.addError(doctorExistsError);
        }
    }
    public void emailValidate(String email,BindingResult errors,Locale locale){
        if (userService.userExists(email)) {
            FieldError ssoError = new FieldError("form", "email",messageSource.getMessage("user.exist.error.message",null,locale));
            errors.addError(ssoError);
        }
    } */

   public static boolean photoValidate(MultipartFile photo){
       if(!photo.isEmpty()){
           String contentType = photo.getContentType();
           return !(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/jpg"));
       }
       return false;
   }

   /* public boolean photoValidate(MultipartFile photo) {
        if(!photo.isEmpty()){
            String contentType = photo.getContentType();
            return !(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/jpg"));
        }
        return false;
    } */
}
