package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;

public interface PrepaidToClinicService {
    PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic);
}
