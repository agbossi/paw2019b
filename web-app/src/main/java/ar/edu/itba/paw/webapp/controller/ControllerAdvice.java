package ar.edu.itba.paw.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(HttpServletRequest request, Exception ex){

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("message",messageSource.getMessage("database.error",null, LocaleContextHolder.getLocale()));
        return mav;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("message",messageSource.getMessage("page.not.found",null, LocaleContextHolder.getLocale()));
        return mav;
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", messageSource.getMessage("unexpected.error",null, LocaleContextHolder.getLocale()));
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
