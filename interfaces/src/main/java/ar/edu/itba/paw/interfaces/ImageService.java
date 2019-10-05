package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image createProfileImage(MultipartFile file, Doctor doctor);

    Image getProfileImage(Doctor doctor);
}
