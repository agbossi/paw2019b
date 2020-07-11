package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PrepaidDao;
import ar.edu.itba.paw.interfaces.service.PatientService;
import ar.edu.itba.paw.interfaces.service.PrepaidService;
import ar.edu.itba.paw.model.Patient;
import ar.edu.itba.paw.model.Prepaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
public class PrepaidServiceImpl implements PrepaidService {

    @Autowired
    private PrepaidDao prepaidDao;

    @Autowired
    private PatientService patientService;

    public List<Prepaid> getPrepaids() {
        return prepaidDao.getPrepaids();
    }

    @Override
    public Prepaid getPrepaidByName(String prepaidName) {
        return prepaidDao.getPrepaidByName(prepaidName);
    }

    @Transactional
    @Override
    public Prepaid createPrepaid(String name) {
        return prepaidDao.createPrepaid(name);
    }

    @Transactional
    @Override
    public void updatePrepaid(String oldName, String name) {
        prepaidDao.updatePrepaid(oldName, name);
    }

    @Transactional
    @Override
    public long deletePrepaid(String name) {
        List<Patient> patients = patientService.getPatientsByPrepaid(name);
        long result = prepaidDao.deletePrepaid(name);
        for(Patient patient : patients) {
            System.out.println(patient.getEmail());
            patientService.updatePatient(patient.getEmail(), null ,null);
        }
        return result;
    }

    @Override
    public List<Prepaid> getPaginatedObjects(int page) {
        if(page < 0) {
            return Collections.emptyList();
        }
        return prepaidDao.getPaginatedObjects(page);
    }

    @Override
    public int maxAvailablePage() {
        return prepaidDao.maxAvailablePage();
    }
}
