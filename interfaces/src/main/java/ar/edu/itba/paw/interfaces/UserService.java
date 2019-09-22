package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.User;

public interface UserService {
    public User createUser(String id, String firstName,String lastName, String password, String email, String healthInsurance);

    public User findUserByEmail(String email);

    public boolean userExists(String email);
}
