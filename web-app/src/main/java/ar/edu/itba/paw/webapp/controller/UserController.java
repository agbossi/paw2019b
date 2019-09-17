package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.webapp.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signUp", method = {RequestMethod.GET})
    public ModelAndView signUp(@ModelAttribute("signUpForm") final SignUpForm form){
        ModelAndView mav = new ModelAndView("signUp");

        return mav;
    }
    @RequestMapping(value = "/signUp",method = {RequestMethod.POST})
    public ModelAndView signUpValidation(@Valid @ModelAttribute("signUpForm") final SignUpForm form, final BindingResult errors){
        //does not seem to validate correctly
        if(errors.hasErrors()){
            return signUp(form);
        }
        userService.createUser(form.getId(),form.getFirstName(),form.getLastName(),form.getPassword(),form.getEmail(),form.getHealthInsurance());
        //TODO how to send back to the previous page with its parameters
        //possible solution: request curr url in <a href = ${}/signUp> and use @pathvariable in controller
        final ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}
