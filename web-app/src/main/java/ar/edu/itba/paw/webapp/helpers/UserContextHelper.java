package ar.edu.itba.paw.webapp.helpers;
import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserContextHelper {

    // Private constructor to prevent instantiation
    private UserContextHelper() {
        throw new UnsupportedOperationException();
    }

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

    public static void saveUserQuery(SearchForm form, HttpServletRequest request){
        HttpSession session = request.getSession();

        session.setAttribute("firstName",form.getFirstName());
        session.setAttribute("lastName",form.getLastName());
        session.setAttribute("consultPrice",form.getConsultPrice());
        session.setAttribute("location",form.getLocation());
        session.setAttribute("prepaid",form.getPrepaid());
        session.setAttribute("specialty",form.getSpecialty());
    }

    public static void loadUserQuery(SearchForm form,HttpServletRequest request){
        HttpSession session = request.getSession();

        form.setFirstName((String)session.getAttribute("firstName"));
        form.setLastName((String)session.getAttribute("lastName"));
        form.setConsultPrice((int)session.getAttribute("consultPrice"));
        form.setLocation((String)session.getAttribute("location"));
        form.setPrepaid((String)session.getAttribute("prepaid"));
        form.setSpecialty((String)session.getAttribute("specialty"));

//        session.removeAttribute("firstName");
//        session.removeAttribute("lastName");
//        session.removeAttribute("consultPrice");
//        session.removeAttribute("location");
//        session.removeAttribute("prepaid");
//        session.removeAttribute("specialty");

    }
}
