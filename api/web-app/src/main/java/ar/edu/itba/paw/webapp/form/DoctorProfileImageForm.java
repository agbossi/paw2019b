package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.ValidFileType;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.constraints.NotNull;

public class DoctorProfileImageForm {
    @FormDataParam("profileImage")
    @ValidFileType(types = {"image/jpg", "image/png"}, message = "doctor.photo.not.valid")
    @NotNull
    private FormDataBodyPart profileImage;

    public FormDataBodyPart getProfilePicture() {
        return profileImage;
    }

    public void setProfilePicture(FormDataBodyPart profilePicture) {
        this.profileImage = profilePicture;
    }

    public byte[] getProfilePictureBytes() {
        return profileImage.getValueAs(byte[].class);
    }
}
