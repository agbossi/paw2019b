package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.model.User;
import ar.edu.itba.paw.persistence.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoImpl userDao;

    @Override
    public User createUser(String id, String name, String password, String email, String healthInsurance) {
        return userDao.createUser(id,name,password,email,healthInsurance);
    }
}
