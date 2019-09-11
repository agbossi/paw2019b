package ar.edu.itba.paw.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontController {

    @RequestMapping("/")
    public ModelAndView index(){
        final ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
