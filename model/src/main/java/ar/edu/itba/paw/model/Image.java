package ar.edu.itba.paw.model;

import java.io.InputStream;

public class Image {
    private String license;

    private InputStream image;

    public Image(String license, InputStream image) {
        this.license = license;
        this.image = image;
    }

    public String getLicense() {
        return license;
    }

    public InputStream getImage() {
        return image;
    }
}
