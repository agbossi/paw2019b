package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.webapp.form.SearchForm;
import ar.edu.itba.paw.webapp.helpers.ModelAndViewModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FrontController {

    @Autowired
    private ModelAndViewModifier viewModifier;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("searchForm") final SearchForm form){
        final ModelAndView mav = new ModelAndView("index");
        viewModifier.addSearchInfo(mav);
        viewModifier.addDoctors(mav);

        return mav;
    }
}
