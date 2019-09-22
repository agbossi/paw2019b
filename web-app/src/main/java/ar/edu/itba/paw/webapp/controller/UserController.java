package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.webapp.auth.PawUrlAuthenticationSuccessHandler;
import ar.edu.itba.paw.webapp.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationSuccessHandler urlAuthenticationSuccessHandler;

    //TODO change form error messages
    @RequestMapping(value = "/signUp", method = { RequestMethod.GET })
    public ModelAndView signUp(@ModelAttribute("signUpForm") final SignUpForm form,HttpServletRequest request,HttpServletResponse response){
        //TODO how to send back to the previous page with its parameters
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);
        //

        final ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }
    @RequestMapping(value = "/signUp",method = { RequestMethod.POST })
    public ModelAndView signUpValidation(@Valid @ModelAttribute("signUpForm") final SignUpForm form, final BindingResult errors, HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //TODO message does not display
        if(!form.getPassword().equals(form.getRepeatPassword())){
            FieldError passwordNotMatchingError = new FieldError("form","repeat password","fields password and repeat password do not match");
            errors.addError(passwordNotMatchingError);
        }

        if (userService.userExists(form.getEmail())) {
            //TODO change message for language variable
            FieldError ssoError = new FieldError("form", "email","That email is already registered" );
            errors.addError(ssoError);
        }

        if(errors.hasErrors()){
            return signUp(form,request,response);
        }

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        userService.createUser(form.getId(),form.getFirstName(),form.getLastName(),encodedPassword,form.getEmail(),form.getHealthInsurance());
        //to be kept logged in after sign up

        //TODO this should send an email or something of confirmation and then login ???
        //authWithAuthManager(request, form.getEmail(), encodedPassword);

        //TODO how to send back to the previous page with its parameters
        //need to get parameter stored in request

        //not sure if it gets here, should not but does
        //TODO change redirect parameter once obtained
        final ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }

    @RequestMapping(value = "/login",method = { RequestMethod.GET })
    public ModelAndView login(HttpServletRequest request){
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);
        return new ModelAndView("login");
    }

    private void authWithAuthManager(HttpServletRequest request, String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
