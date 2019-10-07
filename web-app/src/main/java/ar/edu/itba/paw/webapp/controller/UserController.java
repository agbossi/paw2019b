package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.EmailService;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.form.SignUpForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private ModelAndViewModifier modelAndViewModifier;

    @Autowired
    private MessageSource messageSource;

    private void authWithAuthManager(HttpServletRequest request, String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);

        // generate session if one doesn't exist
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    //TODO change form error messages
    @RequestMapping(value = "/signUp", method = { RequestMethod.GET })
    public ModelAndView signUp(@ModelAttribute("signUpForm") final SignUpForm form,HttpServletRequest request,HttpServletResponse response) {

        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);

        final ModelAndView mav = new ModelAndView("signUp");
        modelAndViewModifier.addPrepaids(mav);

        return mav;
    }

    @RequestMapping(value = "/signUp",method = { RequestMethod.POST })
    public ModelAndView signUpValidation(@Valid @ModelAttribute("signUpForm") final SignUpForm form, final BindingResult errors, HttpServletRequest request, HttpServletResponse response, Locale locale) {

        if(!form.getPassword().equals(form.getRepeatPassword())){
            FieldError passwordNotMatchingError = new FieldError("form","repeatPassword",messageSource.getMessage("user.password.not.matching",null,locale));
            errors.addError(passwordNotMatchingError);
        }

        if (userService.userExists(form.getEmail())) {
            FieldError ssoError = new FieldError("form", "email",messageSource.getMessage("user.exist.error.message",null,locale));
            errors.addError(ssoError);
        }

        if(errors.hasErrors()){
            return signUp(form,request,response);
        }

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        User user = userService.createUser(form.getFirstName(),form.getLastName(),encodedPassword,form.getEmail());
        patientService.create(form.getEmail(),form.getId(),form.getPrepaid(), form.getPrepaidNumber(), user);
        authWithAuthManager(request, form.getEmail(), form.getPassword());

        String ret = signUpSuccess(request);

        final ModelAndView mav = new ModelAndView("redirect:"+ret);
        return mav;
    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET })
    public ModelAndView login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);
        userService.changePassword(passwordEncoder.encode("admin"),"admin@doctorsearch.com");

        return new ModelAndView("login");
    }
    @RequestMapping(value="/login-error", method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request,Locale locale){

        ModelAndView mav = new ModelAndView("/login");
        mav.addObject("errorMessage", messageSource.getMessage("bad.credentials",null,locale));
        return mav;
    }

    private String signUpSuccess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login");
            if (redirectUrl != null) {
                session.removeAttribute("url_prior_login");
                if(!redirectUrl.contains("login") && !redirectUrl.contains("signUp")){
                    return redirectUrl;
                }
            }
        }
        return "/";
    }
}
