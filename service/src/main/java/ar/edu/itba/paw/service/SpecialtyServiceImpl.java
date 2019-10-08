package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.SpecialtyDao;
import ar.edu.itba.paw.interfaces.service.SpecialtyService;
import ar.edu.itba.paw.model.Specialty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyDao specialtyDao;

    @Transactional
    @Override
    public Specialty createSpecialty(String name){
        return specialtyDao.createSpecialty(name);
    }

    @Override
    public List<Specialty> getSpecialties(){
        return specialtyDao.getSpecialties();
    }

    @Override
    public Specialty getSpecialtyByName(String specialtyName) {
        return specialtyDao.getSpecialtyByName(specialtyName);
    }
}
