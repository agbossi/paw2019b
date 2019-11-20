package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.PatientDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {

    private static final User user = new User("patFirstName", "patLastName", "password", "patient@mail.com");

    private static final String id = "332233";

    private static final Prepaid prepaid = new Prepaid("prepaid");

    private static final String prepaidNumber = "123231";

    @InjectMocks
    private PatientServiceImpl patientService = new PatientServiceImpl();

    @Mock
    private PatientDao mockDao;

    @Mock
    private UserService userService;

    @Test
    public void testCreate(){
        //Set Up
        Mockito.when(mockDao.create(Mockito.eq(id),Mockito.eq(prepaid.getName()),
                Mockito.eq(prepaidNumber), Mockito.eq(user)))
                .thenReturn(new Patient(id, prepaid.getName(), prepaidNumber, user));
        Mockito.when(userService.createUser(Mockito.eq(user.getFirstName()), Mockito.eq(user.getLastName()),
                Mockito.eq(user.getPassword()), Mockito.eq(user.getEmail()))).thenReturn(user);
        //Execute
        Patient patient = patientService.create(id, prepaid.getName(), prepaidNumber, user.getFirstName(), user.getLastName(), user.getPassword(), user.getEmail());

        //Assert
        Assert.assertNotNull(patient);
        Assert.assertEquals(user.getEmail(), patient.getEmail());
        Assert.assertEquals(id, patient.getId());
        Assert.assertEquals(prepaid.getName(), patient.getPrepaid());
        Assert.assertEquals(prepaidNumber, patient.getPrepaidNumber());
        Assert.assertEquals(user.getLastName(), patient.getLastName());
        Assert.assertEquals(user.getFirstName(), patient.getFirstName());

    }
}
