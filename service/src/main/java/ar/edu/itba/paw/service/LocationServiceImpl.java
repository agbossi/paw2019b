package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.LocationDao;
import ar.edu.itba.paw.interfaces.LocationService;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public Location createLocation(String name) {
        return locationDao.createLocation(name);
    }

    @Override
    public List<Location> getLocations() {
        return locationDao.getLocations();
    }
}
