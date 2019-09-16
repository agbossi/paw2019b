package ar.edu.itba.paw.model;

public class User {
    private String name;

    private String id;

    private String password;

    private String healthInsurance;

    private String email;

    public User(String name, String id, String password, String healthInsurance, String email) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.healthInsurance = healthInsurance;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
