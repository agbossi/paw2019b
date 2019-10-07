package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PrepaidToClinicDao;
import ar.edu.itba.paw.interfaces.service.PrepaidToClinicService;
import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.PrepaidToClinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrepaidToClinicServiceImpl implements PrepaidToClinicService {

    @Autowired
    PrepaidToClinicDao prepaidToClinicDao;

    @Override
    public PrepaidToClinic addPrepaidToClinic(Prepaid prepaid, Clinic clinic) {
        return prepaidToClinicDao.addPrepaidToClinic(prepaid,clinic);
    }
}
