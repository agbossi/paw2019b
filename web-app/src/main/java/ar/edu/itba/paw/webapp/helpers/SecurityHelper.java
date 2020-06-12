package ar.edu.itba.paw.webapp.helpers;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityHelper {
    public static String processNewPassword(String newPass, PasswordEncoder passwordEncoder) {
        String pass = null;
        if(newPass.equals("")) pass = passwordEncoder.encode(newPass);
        return pass;
    }
}
