package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    long createProfileImage(MultipartFile file, Doctor doctor);

    long updateProfileImage(MultipartFile file, Doctor doctor);

    Image getProfileImage(String license);


}
