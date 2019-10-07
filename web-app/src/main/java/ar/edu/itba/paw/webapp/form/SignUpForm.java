package ar.edu.itba.paw.webapp.form;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpForm {

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String password;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String repeatPassword;

    @Size(min = 6, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    private String email;

    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String prepaid;

    @Size(max=20)
    @Pattern(regexp = "[0-9]*")
    private String prepaidNumber;

    @Size(min = 8,max = 8)
    @Pattern(regexp = "[0-9]+")
    private String id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public String getPrepaidNumber() {
        return prepaidNumber;
    }

    public void setPrepaidNumber(String prepaidNumber) {
        this.prepaidNumber = prepaidNumber;
    }

    public String getId() {
        return id;
    }
}
