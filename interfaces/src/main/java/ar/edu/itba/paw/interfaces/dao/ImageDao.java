package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;

public interface ImageDao {

    Image createProfileImage(byte[] image, String doctor);

    long updateProfileImage(byte [] image, String doctor);

    void deleteProfileImage(Image profileImage);

    Image getProfileImage(String doctor);


}
