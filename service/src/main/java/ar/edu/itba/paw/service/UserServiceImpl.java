package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.DoctorDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    DoctorDao doctorDao;

    @Override
    public User createUser(String firstName,String lastName, String password, String email) {
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

    @Override
    public void changePassword(String password, String email) {
        userDao.changePassword(password,email);
    }

}
