package ar.edu.itba.paw.model;

import java.io.InputStream;

public class Image {
    private long id;

    private String license;

    private InputStream image;

    // TODO: analizar si es necesario algun constructor solo con ID

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
}
