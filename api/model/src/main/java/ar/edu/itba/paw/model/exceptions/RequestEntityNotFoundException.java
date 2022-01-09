package ar.edu.itba.paw.model.exceptions;

public class RequestEntityNotFoundException extends Exception {
    public RequestEntityNotFoundException(String entity) {
        super(entity + "-not-found");
    }
}
