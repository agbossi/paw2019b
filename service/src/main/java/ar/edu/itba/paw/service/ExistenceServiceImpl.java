package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistenceServiceImpl implements ExistenceService {

    @Autowired
    DoctorService doctorService;

    @Autowired
    UserService userService;

    @Autowired
    PrepaidService prepaidService;

    @Autowired
    LocationService locationService;

    @Autowired
    SpecialtyService specialtyService;


    //falta doctorClinic,prepaidToclinic porque usa los dos campos,Clinic por el id. schedule y appointment creo que no son validables asi

    @Override
    public boolean exists(String input,String type) {
        switch(type){
            case "doctor":
                return doctorService.isDoctor(input);
            case "user":
                return userService.userExists(input);
            case "prepaid":
                return prepaidService.getPrepaidByName(input) != null;
            case "specialty":
                return specialtyService.getSpecialtyByName(input) != null;
            case "location":
                return locationService.getLocationByName(input) != null;
            default:
                return true;
        }
    }
}
