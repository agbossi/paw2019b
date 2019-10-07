package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.EmailService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DoctorDao doctorDao;


    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageSource messageSource;

    @Transactional
    @Override
    public User createUser(String firstName,String lastName, String password, String email) {
        Locale locale = LocaleContextHolder.getLocale();
        emailService.sendSimpleMail(email,messageSource.getMessage("sign.up.subject",null,locale),firstName + " " + " " + lastName + " " +messageSource.getMessage("sign.up.message",null,locale));
        return userDao.createUser(firstName,lastName,password,email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public boolean userExists(String email) {
        return (userDao.findUserByEmail(email) != null);
    }

    @Override
    public boolean isDoctor(String email) {
        return doctorDao.isDoctor(email);
    }

    @Transactional
    @Override
    public void changePassword(String password, String email) {
        userDao.changePassword(password,email);
    }

}
