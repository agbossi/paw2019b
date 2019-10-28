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
    @JoinColumn(name = "license")
    private String license;

    //esto no se como
    private InputStream image;

    public Image(long id, String license, InputStream image) {
        this.id = id;
        this.license = license;
        this.image = image;
    }

    public Image(){

    }

    public long getId() {
        return id;
    }

    public String getLicense() {
        return license;
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
        this.license = license;
    }
}
