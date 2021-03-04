package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.webapp.helpers.validation.annotations.ValidFileType;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

public class DoctorProfileImageForm {
    @FormDataParam("profilePicture")
    @ValidFileType(types = {"image/jpg", "image/png"}, message = "{doctor.photo.not.valid}")
    private FormDataBodyPart profilePicture;

    public FormDataBodyPart getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(FormDataBodyPart profilePicture) {
        this.profilePicture = profilePicture;
    }

    public byte[] getProfilePictureBytes() {
        return profilePicture.getValueAs(byte[].class);
    }
}
