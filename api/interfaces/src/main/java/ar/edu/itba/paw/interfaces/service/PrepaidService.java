package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.Prepaid;

import java.util.List;

public interface PrepaidService extends PaginationService<Prepaid> {
    List<Prepaid> getPrepaids();

    Prepaid getPrepaidByName(String PrepaidName);

    Prepaid createPrepaid(String name);

    void updatePrepaid(String oldName, String name);

    long deletePrepaid(String name);
}
