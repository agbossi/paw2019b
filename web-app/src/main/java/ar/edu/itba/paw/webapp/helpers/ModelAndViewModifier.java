package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    DoctorClinicService doctorClinicService;

    public ModelAndView addSearchInfo(ModelAndView mav){
        List<Location> locations = locationService.getLocations();
        List<Specialty> specialties = specialtyService.getSpecialties();
        List<Clinic> clinics = clinicService.getClinics();
        mav.addObject("locations", locations);
        mav.addObject("specialties", specialties);
        mav.addObject("clinics", clinics);

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

    public ModelAndView addDoctors(ModelAndView mav){
        List<DoctorClinic> doctors = doctorClinicService.getDoctorClinics();
        mav.addObject("doctorClinics", doctors);

        return mav;
    }

    public ModelAndView addFilteredDoctors(ModelAndView mav, List<DoctorClinic> filteredDoctors){
        mav.addObject("doctorClinics", filteredDoctors);

        return mav;
    }
}
