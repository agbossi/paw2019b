package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PrepaidToClinicDao;
import ar.edu.itba.paw.interfaces.service.PrepaidToClinicService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrepaidToClinicServiceImpl implements PrepaidToClinicService {

    @Autowired
    PrepaidToClinicDao prepaidToClinicDao;

    @Override
    public List<PrepaidToClinic> getPrepaidToClinics() {
        return prepaidToClinicDao.getPrepaidToClinics();
    }

    @Transactional
    @Override
    public PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic) {
        return prepaidToClinicDao.addPrepaidToClinic(prepaid,clinic);
    }

    @Override
    public boolean clinicHasPrepaid(String prepaid, int clinic) {
        return prepaidToClinicDao.clinicHasPrepaid(prepaid,clinic);
    }

    @Transactional
    @Override
    public long deletePrepaidFromClinic(String prepaid, int clinic) {
        return prepaidToClinicDao.deletePrepaidFromClinic(prepaid, clinic);
    }
}
