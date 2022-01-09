package ar.edu.itba.paw.model.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String entity) {
        super(entity + "-not-found");
    }
}
