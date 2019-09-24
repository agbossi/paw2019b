package ar.edu.itba.paw.webapp.helpers;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

public class UserContextHelper {

    public static String getLoggedUserEmail(SecurityContext context) {

        // principal refers to currently authenticated user
        Object principal = context.getAuthentication().getPrincipal();

        String email;
        if(principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }

    public static User getLoggedUser(SecurityContext context, UserService userService) {
        return userService.findUserByEmail(getLoggedUserEmail(context));
    }
}