package ar.edu.itba.paw.webapp.helpers;

import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityHelper {

    public static String processNewPassword(String newPass, PasswordEncoder passwordEncoder, UserService userService, String userEmail) {
        String pass;
        if(newPass == null) {
            User user = userService.findUserByEmail(userEmail);
            pass = user.getPassword();// TODO mismo tema con user logueado y eso
        } else {
            pass = passwordEncoder.encode(newPass);
        }
        return pass;
    }
}
