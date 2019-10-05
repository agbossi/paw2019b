package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;

public interface ImageDao {

    Image createProfileImage(byte[] image, Doctor doctor);

    Image getProfileImage(Doctor doctor);
}
