package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.AppointmentDao;
import ar.edu.itba.paw.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AppointmentDaoImplTest {

    private static final Doctor doc = new Doctor("docFirstName", "docLastName", new Specialty("specialty"), "1", "1234567890","doctor@mail.com");

    private static final Clinic clinic = new Clinic(1,"clinic", "address", new Location("location"));

    private static final DoctorClinic doctorClinic = new DoctorClinic(doc, clinic, 1);

    private static final User user = new User("patFirstName", "patLastName", "password", "patient@mail.com");
    
    private static final Doctor doc2 = new Doctor("docFirstName2", "docLastName2", new Specialty("specialty"), "2", "1234567890","doctor2@mail.com");

    private static final DoctorClinic doctorClinic2 = new DoctorClinic(doc2, clinic, 1);

    private static final User user2 = new User("patFirstName2", "patLastName2", "password", "patient2@mail.com");

    private static final Calendar cal = Calendar.getInstance();


    @Autowired
    private DataSource ds;

    @Autowired
    private AppointmentDaoImpl appointmentDao;

    private JdbcTemplate jdbcTemplate;


    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);

        cal.set(2019,9,1,8,0,0);
        cal.set(Calendar.MILLISECOND, 0);
    }

    @Test
    public void testCreate(){
        final Appointment appointment = appointmentDao.createAppointment(doctorClinic2, user2, cal);

        assertNotNull(appointment);
        assertEquals(doctorClinic2.getDoctor().getLicense(), appointment.getDoctorClinic().getDoctor().getLicense());
        assertEquals(doctorClinic2.getClinic().getId(), appointment.getDoctorClinic().getClinic().getId());
        assertEquals(user2.getEmail(), appointment.getPatient().getEmail());
        assertEquals(cal, appointment.getDate());

        assertEquals(2, JdbcTestUtils.countRowsInTable(jdbcTemplate, "appointments"));

    }

    @Test
    public void testHasAppointment(){
        final Appointment appointment = appointmentDao.hasAppointment(doctorClinic, cal);

        assertNotNull(appointment);
        assertEquals(doctorClinic.getDoctor().getLicense(), appointment.getDoctorClinic().getDoctor().getLicense());
        assertEquals(doctorClinic.getClinic().getId(), appointment.getDoctorClinic().getClinic().getId());
        assertEquals(cal, appointment.getDate());

    }

    @Test
    public void testGetAllDoctorsAppointments(){
        final List<Appointment> apps = appointmentDao.getAllDoctorsAppointments(doc);
        assertNotNull(apps);
        Assert.assertTrue(!apps.isEmpty());
        assertEquals(doc.getLicense(), apps.get(0).getDoctorClinic().getDoctor().getLicense());

    }

    @Test
    public void testGetAllPatientsAppointments(){
        final List<Appointment> apps = appointmentDao.getPatientsAppointments(user);
        assertNotNull(apps);
        Assert.assertTrue(!apps.isEmpty());
        assertEquals(doc.getLicense(), apps.get(0).getDoctorClinic().getDoctor().getLicense());

    }

    @Test
    public void testCancelAppointment(){
        appointmentDao.cancelAppointment(doctorClinic, user, cal);

        assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "appointments"));
    }
}
