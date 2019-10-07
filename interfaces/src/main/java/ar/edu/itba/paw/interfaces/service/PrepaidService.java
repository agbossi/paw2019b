package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Prepaid;

import java.util.List;

public interface PrepaidService {
    List<Prepaid> getPrepaids();

    Prepaid createPrepaid(String name);
}
