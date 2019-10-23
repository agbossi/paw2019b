package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.User;

public interface UserService {
    User createUser(String firstName,String lastName, String password, String email);

    User findUserByEmail(String email);

    boolean userExists(String email);

    boolean isDoctor(String email);

    void changePassword(String password,String email);

    void updateUser(String email, String newPassword, String firstName, String lastName);
}
