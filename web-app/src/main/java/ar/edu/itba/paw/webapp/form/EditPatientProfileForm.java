package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditPatientProfileForm {

    // User information

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;

    @Size(min = 1, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;

    @Size(min = 6, max = 25)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    private String email;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String oldPassword;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String newPassword;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String repeatNewPassword;

    // Patient information

    @Size(min = 8,max = 8)
    @Pattern(regexp = "[0-9]+")
    private String id;

    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String prepaid;

    @Size(max=20)
    @Pattern(regexp = "[0-9]*")
    private String prepaidNumber;
}
