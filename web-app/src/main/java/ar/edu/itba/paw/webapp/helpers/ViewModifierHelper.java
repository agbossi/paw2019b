package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.service.*;
import ar.edu.itba.paw.model.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

public class ViewModifierHelper {

    // Private constructor to prevent instantiation
    private ViewModifierHelper() {
        throw new UnsupportedOperationException();
    }

    public static ModelAndView addPaginatedDoctors(ModelAndView mav, List<String> licenses,
                                                   DoctorService doctorService, int page) {
        List<Doctor> doctors = doctorService.getPaginatedDoctors(licenses, page);
        int maxAvailablePage = doctorService.getMaxAvailableDoctorsPage(licenses);
        mav.addObject("doctors", doctors);
        mav.addObject("maxPage", maxAvailablePage);
        mav.addObject("page", page);
        return mav;
    }

    public static ModelAndView addSearchInfo(ModelAndView mav, LocationService locationService,
                                             SpecialtyService specialtyService, ClinicService clinicService,
                                             PrepaidService prepaidService){
        addLocations(mav, locationService);
        addSpecialties(mav, specialtyService);
        addClinics(mav, clinicService);
        addPrepaids(mav, prepaidService);

        return mav;
    }

    public static ModelAndView addLocations(ModelAndView mav, LocationService locationService) {
        List<Location> locations = locationService.getLocations();
        mav.addObject("locations", locations);
        return mav;
    }

    public static <T> ModelAndView addPaginatedObjects(ModelAndView mav, PaginationService<T> paginationService, int page){
        List<T> objects = paginationService.getPaginatedObjects(page);
        int maxPageAvailable = paginationService.maxAvailablePage();
        mav.addObject("objects", objects);
        mav.addObject("page", page);
        mav.addObject("maxPage", maxPageAvailable);
        System.out.println("La pagina maxima es " + maxPageAvailable);
        return mav;
    }

    public static ModelAndView addSpecialties(ModelAndView mav, SpecialtyService specialtyService){
        List<Specialty> specialties = specialtyService.getSpecialties();
        Collections.sort(specialties, new Comparator<Specialty>() {
            @Override
            public int compare(Specialty s1, Specialty s2) {
                return s1.getSpecialtyName().toLowerCase().compareTo(s2.getSpecialtyName().toLowerCase());
            }
        });
        mav.addObject("specialties", specialties);
        return mav;
    }

    public static ModelAndView addClinics(ModelAndView mav, ClinicService clinicService){
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

    public static ModelAndView addDoctorClinicsForDoctor(ModelAndView mav, Doctor doctor, DoctorClinicService doctorClinicService){
        List<DoctorClinic> doctors = doctorClinicService.getDoctorClinicsForDoctor(doctor);
        mav.addObject("doctorClinics", doctors);
        return mav;
    }

    public static ModelAndView addDoctors(ModelAndView mav, DoctorService doctorService){
        List<Doctor> doctors = doctorService.getDoctors();
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
        mav.addObject("doctors", doctors);
        return mav;
    }

    public static ModelAndView addDoctorsWithAvailability(ModelAndView mav, DoctorService doctorService){
        List<Doctor> doctors = doctorService.getDoctorsWithAvailability();
        mav.addObject("doctors", doctors);
        return mav;
    }

    public static ModelAndView addFilteredDoctors(ModelAndView mav, List<Doctor> filteredDoctors){
        mav.addObject("doctors", filteredDoctors);

        return mav;
    }
    public static ModelAndView addPrepaids(ModelAndView mav, PrepaidService prepaidService){
        List<Prepaid> prepaids = prepaidService.getPrepaids();
        Collections.sort(prepaids, new Comparator<Prepaid>() {
            @Override
            public int compare(Prepaid p1, Prepaid p2) {
                return p1.getName().toLowerCase().compareTo(p2.getName().toLowerCase());
            }
        });
        mav.addObject("prepaids", prepaids);
        return mav;
    }

    public static ModelAndView addPrepaidClinics(ModelAndView mav, PrepaidToClinicService prepaidToClinicService){
        List<PrepaidToClinic> prepaidClinics = prepaidToClinicService.getPrepaidToClinics();
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
        mav.addObject("prepaidClinics", prepaidClinics);
        return mav;
    }

    public static ModelAndView addDaysAdnTimes(ModelAndView mav){
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

    public static ModelAndView addCurrentDates(ModelAndView mav, int week){
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
