package ar.edu.itba.paw.model;

import javax.persistence.*;
import java.io.InputStream;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToOne
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    @Column(name = "image")
    private InputStream image;

    public Image(long id, Doctor doctor, InputStream image) {
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLicense(String license) {
        this.doctor.setLicense(license);
    }
}
