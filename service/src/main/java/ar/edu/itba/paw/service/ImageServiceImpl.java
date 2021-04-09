package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.ImageDao;
import ar.edu.itba.paw.interfaces.service.DoctorService;
import ar.edu.itba.paw.interfaces.service.ImageService;
import ar.edu.itba.paw.model.Doctor;
import ar.edu.itba.paw.model.Image;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Transactional
    @Override
    // TODO: check if there's an more elegant way of doing this
    public long createProfileImage(MultipartFile file, Doctor doctor) {
        try {
             return imageDao.createProfileImage(file.getBytes(), doctor.getLicense());
        }
        catch (IOException e){
            return -1; // means wrong id for image (error)
        }
    }

    @Transactional
    @Override
    public long updateProfileImage(MultipartFile file, Doctor doctor) {
        if(file.isEmpty()) return 0;
        if(imageDao.getProfileImage(doctor.getLicense()) != null) {
            try {
                return imageDao.updateProfileImage(file.getBytes(), doctor.getLicense());
            }
            catch (IOException e){
                return -1; // means wrong id for image (error)
            }
        }
        else return createProfileImage(file, doctor);
    }

    @Override
    public Image getProfileImage(String license) {
        Image image = imageDao.getProfileImage(license);
        if(image == null) {
            try { //TODO cambiar path para abrirlo en cualquier lado
                File file = new File("/home/abossi/Desktop/paw-2019b-4/web-app/src/main/webapp/resources/images/docpic.jpg");

                BufferedImage bufferedImage = ImageIO.read(file);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                return new Image(-1,null, baos.toByteArray());
            } catch (IOException e) {
                LOGGER.error("Incorrect default image path",e);
                return null;
            }
        } else {
            return image;
        }
    }
}
