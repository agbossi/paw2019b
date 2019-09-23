package ar.edu.itba.paw.model;

public class User {
    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String id;

    public User(String id,String firstName,String lastName,String password,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
