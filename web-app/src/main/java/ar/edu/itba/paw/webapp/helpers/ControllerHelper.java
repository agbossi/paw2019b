package ar.edu.itba.paw.webapp.helpers;

import org.springframework.web.multipart.MultipartFile;

public class ControllerHelper {

    /*
    * All edit information methods return error code
    * */

    public static long updateUserInformation(String firstName, String lastName, String email,
                                      String oldPassword, String newPassword, String repeatPassword){
        /*
        * TODO: Here it should call to all needed services
        * */
        return 1;
    }

    public static long updateDoctorInformation(String license, String specialty, String phoneNumber){
        /*
         * TODO: Here it should call to all needed services
         * */
        return 1;
    }

    public static long updateProfilePicture(MultipartFile profilePicture) {
        /*
         * TODO: Here it should call to all needed services
         * */
        return 1;
    }

    public static long updatePatientInformation(String prepaid, String prepaidNumber){
        /*
         * TODO: Here it should call to all needed services
         * */
        return 1;
    }
}
