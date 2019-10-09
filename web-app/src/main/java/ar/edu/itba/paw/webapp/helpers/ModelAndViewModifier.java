package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
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
    private DoctorClinicService doctorClinicService;

    @Autowired
    private PrepaidToClinicService prepaidToClinicService;

    @Autowired
    private DoctorHourService doctorHourService;

    public ModelAndView addSearchInfo(ModelAndView mav){
        List<Location> locations = locationService.getLocations();
        List<Specialty> specialties = specialtyService.getSpecialties();
        List<Clinic> clinics = clinicService.getClinics();
        List<Prepaid> prepaids = prepaidService.getPrepaids();
        mav.addObject("locations", locations);
        mav.addObject("specialties", specialties);
        mav.addObject("clinics",clinics );
        mav.addObject("prepaids",prepaids);

        return mav;
    }

    public ModelAndView addLocations(ModelAndView mav){
        List<Location> locations = locationService.getLocations();
        Collections.sort(locations, new Comparator<Location>() {
            @Override
            public int compare(Location loc1, Location loc2) {
                return loc1.getLocationName().toLowerCase().compareTo(loc2.getLocationName().toLowerCase());
            }
        });
        mav.addObject("locations", locations);
        return mav;
    }

    public ModelAndView addSpecialties(ModelAndView mav){
        List<Specialty> specialties = specialtyService.getSpecialties();
        mav.addObject("specialties", specialties);
        Collections.sort(specialties, new Comparator<Specialty>() {
            @Override
            public int compare(Specialty s1, Specialty s2) {
                return s1.getSpecialtyName().toLowerCase().compareTo(s2.getSpecialtyName().toLowerCase());
            }
        });
        return mav;
    }

    public ModelAndView addClinics(ModelAndView mav){
        List<Clinic> clinics = clinicService.getClinics();
        mav.addObject("clinics", clinics);
        Collections.sort(clinics, new Comparator<Clinic>() {
            @Override
            public int compare(Clinic c1, Clinic c2) {
                return c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase());
            }
        });
        return mav;
    }

    public ModelAndView addDoctorClinicsForDoctor(ModelAndView mav, Doctor doctor){
        List<DoctorClinic> doctors = doctorClinicService.getDoctorClinicsForDoctor(doctor);
        mav.addObject("doctorClinics", doctors);
        return mav;
    }

    public ModelAndView addDoctors(ModelAndView mav){
        List<Doctor> doctors = doctorService.getDoctors();
        mav.addObject("doctors", doctors);
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                int comparison = d1.getFirstName().toLowerCase().compareTo(d2.getFirstName().toLowerCase());
                if(comparison == 0){
                    comparison = d1.getLastName().toLowerCase().compareTo(d2.getLastName().toLowerCase());
                }
                if(comparison == 0){
                    comparison = d1.getLicense().compareTo(d2.getLicense());
                }
                return comparison;
            }
        });
        return mav;
    }

    public ModelAndView addDoctorsWithAvailability(ModelAndView mav){
        List<Doctor> doctors = doctorService.getDoctorsWithAvailability();
        mav.addObject("doctors", doctors);
        return mav;
    }

    public ModelAndView addFilteredDoctors(ModelAndView mav, List<Doctor> filteredDoctors){
        mav.addObject("doctors", filteredDoctors);

        return mav;
    }
    public ModelAndView addPrepaids(ModelAndView mav){
        List<Prepaid> prepaids = prepaidService.getPrepaids();
        mav.addObject("prepaids", prepaids);
        Collections.sort(prepaids, new Comparator<Prepaid>() {
            @Override
            public int compare(Prepaid p1, Prepaid p2) {
                return p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase());
            }
        });
        return mav;
    }

    public ModelAndView addPrepaidClinics(ModelAndView mav){
        List<PrepaidToClinic> prepaidClinics = prepaidToClinicService.getPrepaidToClinics();
        mav.addObject("prepaidClinics", prepaidClinics);
        Collections.sort(prepaidClinics, new Comparator<PrepaidToClinic>() {
            @Override
            public int compare(PrepaidToClinic p1, PrepaidToClinic p2) {
                int comparison = p1.getClinic().getName().toLowerCase().compareTo(p2.getClinic().getName().toLowerCase());
                if(comparison == 0){
                    comparison = p1.getPrepaid().getName().toLowerCase().compareTo(p2.getPrepaid().getName().toLowerCase());
                }
                return comparison;
            }
        });
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

    public ModelAndView addCurrentDates(ModelAndView mav, int week){
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, 7 * (week - 1));
        Calendar first;
        List<Calendar> month = new ArrayList<>();
        if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            first = date;
            first.add(Calendar.DATE, 1);
        }else if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            first = date;
            first.add(Calendar.DATE, 2);
        }else{
            first = date;
            first.add(Calendar.DATE, -(date.get(Calendar.DAY_OF_WEEK)) + 2);
        }

        for (int i = 0; i < 7; i++){
            Calendar day = Calendar.getInstance();
            day.setTime(first.getTime());
            day.add(Calendar.DATE, i);
            month.add(day);
        }

        mav.addObject("days", month);
        mav.addObject("today", Calendar.getInstance());


        return mav;
    }
}
