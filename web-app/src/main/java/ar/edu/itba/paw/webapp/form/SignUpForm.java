package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpForm {

    @Size(min = 6, max = 50)
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @Size(min = 8,max = 9)
    @Pattern(regexp = "[0-9]+")
    private String id;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "[[a-z]+[A-Z]+[0-9]{4,}]")
    private String password;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "[[a-z]+[A-Z]+[0-9]{4,}]")
    private String repeatPassword;

    @Size(min = 4, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String healthInsurance;

    @Size(min = 6, max = 20)
    //stackOverflow
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    private String email;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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
