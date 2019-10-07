package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;

public interface ImageDao {

    long createProfileImage(byte[] image, String doctor);

    long updateProfileImage(byte [] image, String doctor);

    Image getProfileImage(String doctor);


}
