package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.User;

public interface UserDao {
    User createUser(String id, String name, String password, String email, String healthInsurance);
}
