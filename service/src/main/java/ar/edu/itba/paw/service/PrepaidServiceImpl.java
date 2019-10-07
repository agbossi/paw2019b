package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PrepaidDao;
import ar.edu.itba.paw.interfaces.service.PrepaidService;
import ar.edu.itba.paw.model.Prepaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrepaidServiceImpl implements PrepaidService {

    @Autowired
    private PrepaidDao prepaidDao;

    public List<Prepaid> getPrepaids() {
        return prepaidDao.getPrepaids();
    }

    @Override
    public Prepaid createPrepaid(String name) {
        return prepaidDao.createPrepaid(name);
    }
}
