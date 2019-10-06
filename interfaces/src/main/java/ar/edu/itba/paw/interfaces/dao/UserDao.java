package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.User;

public interface UserDao {
    User createUser(String firstName,String lastName, String password, String email);

    User findUserByEmail(String email);

    void changePassword(String password,String email);
}
