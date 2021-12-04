package ar.edu.itba.paw.model.exceptions;

public class DateInPastException extends Exception{

    public DateInPastException(){
        super("past-date");
    }
}
