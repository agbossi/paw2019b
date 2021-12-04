package ar.edu.itba.paw.model.exceptions;

public class AppointmentAlreadyScheduledException extends Exception {
    public AppointmentAlreadyScheduledException(){
        super("appointment-exists");
    }
}
