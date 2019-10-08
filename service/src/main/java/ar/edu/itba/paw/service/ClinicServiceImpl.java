package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.ClinicDao;
import ar.edu.itba.paw.interfaces.service.ClinicService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicDao clinicDao;

    @Transactional
    @Override
    public Clinic createClinic(String name, String address, Location location) {
        return clinicDao.createClinic(name, address, location);
    }

    @Override
    public List<Clinic> getClinics() {
        return clinicDao.getClinics();
    }

    @Override
    public Clinic getClinicByName(String name) {
        return clinicDao.getClinicByName(name);
    }

    @Override
    public Clinic getClinicById(int id) {
        return clinicDao.getClinicById(id);
    }

    @Override
    public List<Clinic> getClinicsByLocation(String location) {
        return clinicDao.getClinicsByLocation(location);
    }

    @Override
    public boolean clinicExists(String name, String address, String location) {
        return clinicDao.clinicExists(name,address,location);
    }
}
