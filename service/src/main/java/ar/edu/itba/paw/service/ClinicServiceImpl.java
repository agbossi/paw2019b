package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.ClinicDao;
import ar.edu.itba.paw.interfaces.ClinicService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicDao clinicDao;

    @Override
    public Clinic createClinic(String name, Location location, int consultPrice) {
        return clinicDao.createClinic(name, location, consultPrice);
    }

    @Override
    public List<Clinic> getClinics() {
        return clinicDao.getClinics();
    }

    @Override
    public Clinic getClinicByName(String name) {
        return clinicDao.getClinicByName(name);
    }
}
