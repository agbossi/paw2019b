package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface LocationService {
    Location createLocation(String name);

    List<Location> getLocations();
}
