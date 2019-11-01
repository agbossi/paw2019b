package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.webapp.auth.SignUpAuthentication;
import ar.edu.itba.paw.webapp.form.SignUpForm;
import ar.edu.itba.paw.webapp.helpers.ValidationHelper;
import ar.edu.itba.paw.webapp.helpers.ViewModifierHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private SignUpAuthentication signUpAuthentication;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ValidationHelper validator;


    @RequestMapping(value = "/signUp", method = { RequestMethod.GET })
    public ModelAndView signUp(@ModelAttribute("signUpForm") final SignUpForm form,HttpServletRequest request,HttpServletResponse response) {

        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);

        final ModelAndView mav = new ModelAndView("signUp");
        ViewModifierHelper.addPrepaids(mav);

        return mav;
    }

    @RequestMapping(value = "/signUp",method = { RequestMethod.POST })
    public ModelAndView signUpValidation(@Valid @ModelAttribute("signUpForm") final SignUpForm form, final BindingResult errors, HttpServletRequest request, HttpServletResponse response, Locale locale) {

        validator.signUpValidate(form.getPassword(),form.getRepeatPassword(),form.getEmail(),errors,locale);

        if(errors.hasErrors()){
            return signUp(form,request,response);
        }

        String encodedPassword = passwordEncoder.encode(form.getPassword());


        User user = userService.createUser(form.getFirstName(),form.getLastName(),encodedPassword,form.getEmail());
        patientService.create(form.getEmail(),form.getId(),form.getPrepaid(), form.getPrepaidNumber(), user);
        signUpAuthentication.authWithAuthManager(request, form.getEmail(), form.getPassword());

        String ret = signUpAuthentication.signUpSuccess(request);

        return new ModelAndView("redirect:"+ret);
    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET })
    public ModelAndView login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);

        return new ModelAndView("login");
    }
    @RequestMapping(value="/login-error", method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request,Locale locale){

        ModelAndView mav = new ModelAndView("/login");
        mav.addObject("errorMessage", messageSource.getMessage("bad.credentials",null,locale));
        return mav;
    }
}
