package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Location;

import java.util.List;

public interface LocationDao {
    Location createLocation(String name);

    public Location getLocationByName(String locationName);

    public List<Location> getLocations();
}
