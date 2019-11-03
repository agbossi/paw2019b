package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.LocationDao;
import ar.edu.itba.paw.interfaces.service.LocationService;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Transactional
    @Override
    public Location createLocation(String name) {
        return locationDao.createLocation(name);
    }

    @Override
    public List<Location> getLocations() {
        return locationDao.getLocations();
    }

    @Override
    public Location getLocationByName(String locationName) {
        return locationDao.getLocationByName(locationName);
    }

    @Override
    public void updateLocation(String oldName, String name) {
        locationDao.updateLocation(oldName, name);
    }

    @Override
    public long deleteLocation(String name) {
        return locationDao.deleteLocation(name);
    }
}
