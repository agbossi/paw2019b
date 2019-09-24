package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.PatientDao;
import ar.edu.itba.paw.interfaces.PatientService;
import ar.edu.itba.paw.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Override
    public Patient create(String id, String prepaid, String prepaidNumber) {
        return patientDao.create(id, prepaid, prepaidNumber);
    }

    @Override
    public Patient getPatientById(String id) {
        return patientDao.getPatientById(id);
    }
}