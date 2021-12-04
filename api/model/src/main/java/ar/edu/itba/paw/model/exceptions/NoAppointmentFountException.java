package ar.edu.itba.paw.model.exceptions;

public class NoAppointmentFountException extends Exception{
    public NoAppointmentFountException() {
        super("appointment-not-found");
    }
}
