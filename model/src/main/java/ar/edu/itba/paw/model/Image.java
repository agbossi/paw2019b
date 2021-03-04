package ar.edu.itba.paw.model;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Entity
@Table(name = "images")
public class Image {

    @Transient
    private static final Logger LOGGER = LoggerFactory.getLogger(Image.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "images_id_seq")
    @SequenceGenerator(sequenceName = "images_id_seq", name = "images_id_seq")
    private int id;

    @OneToOne
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    @Column(name = "image")
    private byte[] image;

    public Image(int id, Doctor doctor, byte[] image) {
        this.id = id;
        this.doctor = doctor;
        this.image = image;
    }

    public Image(){

    }

    public long getId() {
        return id;
    }

    public String getLicense() {
        return doctor.getLicense();
    }

    public byte[] getImage() {
        if(this.image != null) {
            return this.image;
        }
        try {
            //TODO el path esta bien?
            BufferedImage bufferedImage = ImageIO.read(new File("web-app/src/main/webapp/resources/images/docpic.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            LOGGER.error("Incorrect default image path",e);
            return new byte[] {};
        }
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLicense(String license) {
        this.doctor.setLicense(license);
    }
}
