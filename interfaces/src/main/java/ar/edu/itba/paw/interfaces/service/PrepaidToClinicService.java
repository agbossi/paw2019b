package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;

import java.util.List;

public interface PrepaidToClinicService extends PaginationService<PrepaidToClinic> {
    List<PrepaidToClinic> getPrepaidToClinics();

    PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic);

    boolean clinicHasPrepaid(String prepaid,int clinic);

    long deletePrepaidFromClinic(String prepaid, int clinic);
}
