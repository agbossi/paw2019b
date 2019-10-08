package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;

public interface PrepaidToClinicDao {
    PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic);

    boolean clinicHasPrepaid(String prepaid,int clinic);
}
