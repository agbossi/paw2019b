package ar.edu.itba.paw.model;

import java.io.InputStream;

public class Image {
    private long id;

    private String license;

    private InputStream image;

    public Image(long id, String license, InputStream image) {
        this.id = id;
        this.license = license;
        this.image = image;
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
}
