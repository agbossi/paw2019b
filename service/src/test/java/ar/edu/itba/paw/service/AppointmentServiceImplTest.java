package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.interfaces.service.EmailService;
import ar.edu.itba.paw.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceImplTest {

    private static final Doctor doc = new Doctor("docFirstName", "docLastName", new Specialty("specialty"), "1", "1234567890","doctor@mail.com");

    private static final Clinic clinic = new Clinic(1,"clinic", "address", new Location("location"));

    private static final DoctorClinic doctorClinic = new DoctorClinic(doc, clinic, 1);

    private static final User user = new User("patFirstName", "patLastName", "password", "patient@mail.com");

    private static final Doctor doc2 = new Doctor("docFirstName2", "docLastName2", new Specialty("specialty"), "2", "1234567890","doctor2@mail.com");

    private static final DoctorClinic doctorClinic2 = new DoctorClinic(doc2, clinic, 1);

    private static final User user2 = new User("patFirstName2", "patLastName2", "password", "patient2@mail.com");

    private static final Calendar cal = Calendar.getInstance();



    @InjectMocks
    private AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();

    @Mock
    private AppointmentDao mockDao;

    @Mock
    private EmailService mockEmailService;


    @Test
    public void testCreate(){
        //Set Up
        cal.add(Calendar.YEAR, 3);
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE yyyy-mm-dd hh:mm:ss");
        List<Schedule> s = new ArrayList<>();
        s.add(new Schedule(cal.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.HOUR)));
        doctorClinic2.setSchedule(s);
        Mockito.when(mockDao.createAppointment(Mockito.eq(doctorClinic2),Mockito.eq(user2), Mockito.eq(cal)))
        .thenReturn(new Appointment(cal, doctorClinic2, user2));
        Mockito.doNothing().when(mockEmailService).sendSimpleMail(user2.getEmail(), "Appointment confirmed", "Your appointment is confirmed " + dateFormat.format(date));

        //Execute
        Appointment appointment = appointmentService.createAppointment(doctorClinic2, user2, cal);

        //Assert

        Assert.assertNotNull(appointment);
        Assert.assertEquals(doctorClinic2.getDoctor().getLicense(), appointment.getDoctorClinic().getDoctor().getLicense());
        Assert.assertEquals(doctorClinic2.getClinic().getId(), appointment.getDoctorClinic().getClinic().getId());
        Assert.assertEquals(user2.getEmail(), appointment.getPatient().getEmail());
        Assert.assertEquals(cal, appointment.getDate());



    }

}
