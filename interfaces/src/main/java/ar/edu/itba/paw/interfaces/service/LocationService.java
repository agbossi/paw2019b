package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface LocationService {
    Location createLocation(String name);

    List<Location> getLocations();

    Location getLocationByName(String locationName);

    void updateLocation(String oldName, String name);

    long deleteLocation(String name);
}
