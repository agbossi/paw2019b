package ar.edu.itba.paw.webapp.form;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonalInformationForm {

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String firstName;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z ]+")
    private String lastName;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z0-9 ]*")
    private String newPassword;

    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z0-9 ]*")
    private String repeatPassword;

    private String prepaid;

    @Size(max = 20)
    @Pattern(regexp = "[0-9 ]*")
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String password) {
        this.newPassword = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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
