package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Prepaid;

import java.util.List;

public interface PrepaidDao {

    Prepaid createPrepaid(String name);

    Prepaid getPrepaidByName(String PrepaidName);

    List<Prepaid> getPrepaids();
}
