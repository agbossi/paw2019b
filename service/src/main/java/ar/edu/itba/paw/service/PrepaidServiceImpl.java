package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.PrepaidDao;
import ar.edu.itba.paw.interfaces.PrepaidService;
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
}
