package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.LocationDao;
import ar.edu.itba.paw.interfaces.service.LocationService;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Location> getLocations() { return locationDao.getLocations(); }

    @Override
    public List<Location> getPaginatedObjects(int page) {
        if(page < 0) {
            return new ArrayList<>();
        }
        return locationDao.getPaginatedObjects(page);
    }

    @Override
    public int maxAvailablePage() {
        return locationDao.maxAvailablePage();
    }

    @Override
    public Location getLocationByName(String locationName) {
        return locationDao.getLocationByName(locationName);
    }

    @Transactional
    @Override
    public void updateLocation(String oldName, String name) {
        locationDao.updateLocation(oldName, name);
    }

    @Transactional
    @Override
    public long deleteLocation(String name) {
        return locationDao.deleteLocation(name);
    }
}
