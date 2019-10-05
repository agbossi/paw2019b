package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.User;

public interface UserDao {
    public  User createUser(String firstName,String lastName, String password, String email);

    public User findUserByEmail(String email);

    public void changePassword(String password,String email);
}
