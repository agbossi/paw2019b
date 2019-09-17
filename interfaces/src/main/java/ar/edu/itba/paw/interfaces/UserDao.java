package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.User;

public interface UserDao {
    public  User createUser(String id, String firstName,String lastName, String password, String email, String healthInsurance);

    public User findUserByEmail(String email);
}
