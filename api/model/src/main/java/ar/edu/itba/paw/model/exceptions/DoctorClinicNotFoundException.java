package ar.edu.itba.paw.model.exceptions;

public class DoctorClinicNotFoundException extends Exception{
    public DoctorClinicNotFoundException() {
        super("doctor-clinic-not-found");
    }

}
