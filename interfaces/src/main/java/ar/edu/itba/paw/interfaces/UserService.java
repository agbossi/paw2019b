package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.User;

public interface UserService {
    public User createUser(String firstName,String lastName, String password, String email);

    public User findUserByEmail(String email);

    public boolean userExists(String email);

    public boolean isDoctor(String email);

    public void changePassword(String password,String email);
}
