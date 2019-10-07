package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.ImageDao;
import ar.edu.itba.paw.interfaces.service.ImageService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    @Transactional
    @Override
    public long createProfileImage(MultipartFile file, Doctor doctor) {
        try {
            return imageDao.createProfileImage(file.getBytes(), doctor.getLicense());
        }
        catch (IOException e){
            return 0; // code for error
        }
    }

    @Transactional
    @Override
    public long updateProfileImage(MultipartFile file, Doctor doctor) {
        try {
            return imageDao.updateProfileImage(file.getBytes(), doctor.getLicense());
        }
        catch (IOException e){
            return 0; // code for error
        }
    }

    @Override
    public Image getProfileImage(Doctor doctor) {
        return imageDao.getProfileImage(doctor.getLicense());
    }
}
