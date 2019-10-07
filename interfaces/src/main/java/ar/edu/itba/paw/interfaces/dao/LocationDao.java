package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface LocationDao {
    Location createLocation(String name);

    Location getLocationByName(String locationName);

    List<Location> getLocations();
}