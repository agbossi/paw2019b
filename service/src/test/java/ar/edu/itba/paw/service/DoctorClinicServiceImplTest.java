package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.DoctorClinicDao;
import ar.edu.itba.paw.interfaces.service.AppointmentService;
import ar.edu.itba.paw.interfaces.service.ScheduleService;
import ar.edu.itba.paw.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DoctorClinicServiceImplTest {
    private static final Location location = new Location("location");

    private static final Specialty specialty = new Specialty("specialty");

    private static final User user1 = new User("docFirstName", "docLastName","password", "doctor@mail.com");

    private static final Doctor doc = new Doctor( specialty, "1", "1234567890", user1);

    private static final Clinic clinic = new Clinic(1,"clinic", "address", location);

    private static final int consultPrice = 1;

    private static final Prepaid prepaid = new Prepaid("");

    private static final Prepaid prepaid2 = new Prepaid("prepaid");

    private static final int day = Calendar.MONDAY;

    private static final int hour = 10;

    private static final DoctorClinic doctorClinic = new DoctorClinic(doc, clinic, consultPrice);

    private static final User user2 = new User("docFirstName3", "docLastName3", "password", "doctor3@mail.com");

    private static final Doctor doc2 = new Doctor(specialty, "3", "1234", user2);



    @InjectMocks
    private DoctorClinicServiceImpl doctorClinicService = new DoctorClinicServiceImpl();

    @Mock
    private DoctorClinicDao mockDao;

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private AppointmentService appointmentService;

    @Test
    public void testCreate(){
        //Set Up
        Mockito.when(mockDao.createDoctorClinic(Mockito.eq(doc), Mockito.eq(clinic), Mockito.eq(consultPrice)))
                .thenReturn(new DoctorClinic(doc, clinic, consultPrice));

        //Execute
        DoctorClinic doctorClinic = doctorClinicService.createDoctorClinic(doc, clinic,consultPrice);

        //Asserts
        Assert.assertNotNull(doctorClinic);
        Assert.assertEquals(doc.getLicense(), doctorClinic.getDoctor().getLicense());
        Assert.assertEquals(clinic.getId(), doctorClinic.getClinic().getId());
        Assert.assertEquals(consultPrice, doctorClinic.getConsultPrice());
    }

    @Test
    public void testSetSchedule(){
        //Set Up
        doctorClinic.setSchedule(new ArrayList<Schedule>());
        Mockito.when(scheduleService.createSchedule(Mockito.eq(day), Mockito.eq(hour),Mockito.eq(doctorClinic)))
                .thenReturn(new Schedule(day, hour, doctorClinic));

        //Execute
        doctorClinicService.setSchedule(doctorClinic, day, hour);

        //Assert
        Assert.assertFalse(doctorClinic.getSchedule().isEmpty());
    }

    @Test
    public void testGetDoctorClinicFromDoctorAndClinic(){
        //Set Up
        List<Schedule> s = new ArrayList<>();
        s.add(new Schedule(3,10, doctorClinic));
        Mockito.when(scheduleService.getDoctorClinicSchedule(Mockito.eq(doctorClinic)))
                .thenReturn(s);
        Mockito.when(appointmentService.getDoctorsAppointments(Mockito.eq(doctorClinic)))
                .thenReturn(null);

        Mockito.when(mockDao.getDoctorInClinic(Mockito.eq(doc.getLicense()), Mockito.eq(clinic.getId())))
                .thenReturn(new DoctorClinic(doc, clinic, consultPrice));

        //Execute
        DoctorClinic doctorClinic = doctorClinicService.getDoctorClinicFromDoctorAndClinic(doc, clinic);

        //Assert
        Assert.assertNotNull(doctorClinic);
        Assert.assertNotNull(doctorClinic.getSchedule());
        Assert.assertEquals(1, doctorClinic.getSchedule().size());
        Assert.assertEquals(doc.getLicense(), doctorClinic.getDoctor().getLicense());
        Assert.assertEquals(clinic.getId(), doctorClinic.getClinic().getId());
        Assert.assertNull(doctorClinic.getAppointments());

    }

    @Test
    public void testGetDoctorByWithConsultPrice(){
        //Set Up
        List<DoctorClinic> doctors = new ArrayList<>();
        doctors.add(doctorClinic);
        Mockito.when(mockDao.getFilteredDoctors(Mockito.eq(location),
                Mockito.eq(specialty), Mockito.eq(doc.getFirstName()),
                Mockito.eq(doc.getLastName()), Mockito.eq(prepaid), Mockito.eq(consultPrice)))
                .thenReturn(doctors);

        //Execute
        List<DoctorClinic> docs = doctorClinicService.getFilteredDoctorClinics(location,specialty, doc.getFirstName(), doc.getLastName(), prepaid, consultPrice);

        //Assert
        Assert.assertNotNull(docs);
        Assert.assertEquals(1, docs.size());
        Assert.assertEquals(doc.getFirstName(), doctors.get(0).getDoctor().getFirstName());
        Assert.assertEquals(doc.getLastName(), docs.get(0).getDoctor().getLastName());
        Assert.assertEquals(doc.getSpecialty().getSpecialtyName(), docs.get(0).getDoctor().getSpecialty().getSpecialtyName());


    }

    @Test
    public void testGetDoctorByWithPrepaid(){
        //Set Up
        List<DoctorClinic> doctors = new ArrayList<>();
        doctors.add(doctorClinic);
        Mockito.when(mockDao.getFilteredDoctors(Mockito.eq(location),
                Mockito.eq(specialty), Mockito.eq(doc.getFirstName()),
                Mockito.eq(doc.getLastName()), Mockito.eq(prepaid2), Mockito.eq(consultPrice)))
                .thenReturn(doctors);

        //Execute
        List<DoctorClinic> docs = doctorClinicService.getFilteredDoctorClinics(location,specialty, doc.getFirstName(), doc.getLastName(), prepaid2, consultPrice);

        //Assert
        Assert.assertNotNull(docs);
        Assert.assertEquals(1, docs.size());
        Assert.assertEquals(doc.getFirstName(), doctors.get(0).getDoctor().getFirstName());
        Assert.assertEquals(doc.getLastName(), docs.get(0).getDoctor().getLastName());
        Assert.assertEquals(doc.getSpecialty().getSpecialtyName(), docs.get(0).getDoctor().getSpecialty().getSpecialtyName());


    }

}
