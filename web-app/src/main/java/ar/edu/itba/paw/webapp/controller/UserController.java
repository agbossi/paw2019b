package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.webapp.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //TODO change form error messages
    @RequestMapping(value = "/signUp", method = {RequestMethod.GET})
    public ModelAndView signUp(@ModelAttribute("signUpForm") final SignUpForm form){
        ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }
    @RequestMapping(value = "/signUp",method = {RequestMethod.POST})
    public ModelAndView signUpValidation(@Valid @ModelAttribute("signUpForm") final SignUpForm form, final BindingResult errors){
        //TODO find a better way to do this and replace messages
        if(form.getPassword().equals(form.getRepeatPassword())){
            FieldError passwordNotMatchingError = new FieldError("user","password and repeat password","fields password and repeat password do not match");
            errors.addError(passwordNotMatchingError);
        }

        if (userService.userExists(form.getEmail())) {
            //TODO change message for language variable
            FieldError ssoError = new FieldError("user", "email","That email is already registered" );
            errors.addError(ssoError);
        }

        if(errors.hasErrors()){
            return signUp(form);
        }
        userService.createUser(form.getId(),form.getFirstName(),form.getLastName(),passwordEncoder.encode(form.getPassword()),form.getEmail(),form.getHealthInsurance());
        //TODO how to send back to the previous page with its parameters
        //possible solution: request curr url in <a href = ${}/signUp> and use @pathvariable in controller
        final ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }
    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}
