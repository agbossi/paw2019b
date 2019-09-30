package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Component
public class ModelAndViewModifier {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PrepaidService prepaidService;

    @Autowired
    DoctorClinicService doctorClinicService;

    public ModelAndView addSearchInfo(ModelAndView mav){
        List<Location> locations = locationService.getLocations();
        List<Specialty> specialties = specialtyService.getSpecialties();
        List<Clinic> clinics = clinicService.getClinics();
        mav.addObject("locations", locations);
        mav.addObject("specialties", specialties);
        mav.addObject("clinics",clinics );

        return mav;
    }

    public ModelAndView addLocations(ModelAndView mav){
        List<Location> locations = locationService.getLocations();
        mav.addObject("locations", locations);

        return mav;
    }

    public ModelAndView addSpecialties(ModelAndView mav){
        List<Specialty> specialties = specialtyService.getSpecialties();
        mav.addObject("specialties", specialties);

        return mav;
    }

    public ModelAndView addClinics(ModelAndView mav){
        List<Clinic> clinics = clinicService.getClinics();
        mav.addObject("clinics", clinics);

        return mav;
    }

    public ModelAndView addDoctorClinics(ModelAndView mav){
        List<DoctorClinic> doctors = doctorClinicService.getDoctorClinics();
        mav.addObject("doctorClinics", doctors);

        return mav;
    }

    public ModelAndView addDoctors(ModelAndView mav){
        List<Doctor> doctors = doctorService.getDoctors();
        mav.addObject("doctors", doctors);

        return mav;
    }

    public ModelAndView addFilteredDoctors(ModelAndView mav, List<DoctorClinic> filteredDoctors){
        mav.addObject("doctorClinics", filteredDoctors);

        return mav;
    }
    public ModelAndView addPrepaids(ModelAndView mav){
        List<Prepaid> prepaids = prepaidService.getPrepaids();
        mav.addObject("prepaids", prepaids);

        return mav;
    }

    public ModelAndView addDaysAdnTimes(ModelAndView mav){
        List<Integer> days= new LinkedList<>();
        days.add(Calendar.MONDAY);
        days.add(Calendar.TUESDAY);
        days.add(Calendar.WEDNESDAY);
        days.add(Calendar.THURSDAY);
        days.add(Calendar.FRIDAY);
        mav.addObject("days", days);

        List<Integer> times= new LinkedList<>();
        times.add(8);
        times.add(9);
        times.add(10);
        times.add(11);
        times.add(12);
        times.add(13);
        times.add(14);
        times.add(15);
        times.add(16);
        times.add(17);
        times.add(18);
        times.add(19);

        mav.addObject("times", times);

        return mav;
    }

    public ModelAndView addCurrentDates(ModelAndView mav){
        Calendar date = Calendar.getInstance();
        int firstDay;
        if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            firstDay  = date.get(Calendar.DAY_OF_MONTH) - 6;
        }else{
            firstDay = date.get(Calendar.DAY_OF_MONTH) - date.get(Calendar.DAY_OF_WEEK) + 2;
        }

        mav.addObject("year", date.get(Calendar.YEAR));
        mav.addObject("month",date.get(Calendar.MONTH));
        mav.addObject("firstDay", firstDay);

        return mav;
    }

    
}
