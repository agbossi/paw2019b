package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.*;
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

import java.util.List;

import static org.junit.Assert.*;

@Transactional
@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DoctorClinicDaoImplTest {

    private static final Location location = new Location("location");

    private static final Specialty specialty = new Specialty("specialty");

    private static final Doctor doc = new Doctor("docFirstName", "docLastName", specialty, "1", "1234567890","doctor@mail.com");

    private static final Clinic clinic = new Clinic(1,"clinic", "address", location);

    private static final Doctor doc2 = new Doctor("docFirstName3", "docLastName3", specialty, "3", "1234","doctor3@mail.com");

    private static final int consultPrice = 1;

    @Autowired
    DataSource ds;

    JdbcTemplate jdbcTemplate;

    @Autowired
    private DoctorClinicDaoImpl doctorClinicDao;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Test
    public void testCreate(){
        DoctorClinic doctorClinic = doctorClinicDao.createDoctorClinic(doc2, clinic, consultPrice);

        assertNotNull(doctorClinic);
        assertEquals(doc2.getLicense(), doctorClinic.getDoctor().getLicense());
        assertEquals(clinic.getId(), doctorClinic.getClinic().getId());
        assertEquals(consultPrice, doctorClinic.getConsultPrice());
        assertEquals(3, JdbcTestUtils.countRowsInTable(jdbcTemplate, "doctorclinics"));

    }

    @Test
    public void testGetDoctorClinics(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getDoctorClinics();

        assertNotNull(doctorClinics);
        assertEquals(2, doctorClinics.size());
    }

    @Test
    public void testGetDoctorClinicsForDoctor(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getDoctorClinicsForDoctor(doc);

        assertNotNull(doctorClinics);
        assertEquals(1, doctorClinics.size());
        assertEquals(doc.getLicense(), doctorClinics.get(0).getDoctor().getLicense());
        assertEquals(clinic.getId(), doctorClinics.get(0).getClinic().getId());

    }

    @Test
    public void testGetDoctorsInClinic(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getDoctorsInClinic(clinic.getId());

        assertNotNull(doctorClinics);
        assertEquals(2, doctorClinics.size());
        assertEquals(clinic.getId(), doctorClinics.get(0).getClinic().getId());

    }

    @Test
    public void testGetFilteredDoctorsByLocation(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getFilteredDoctors(location, new Specialty(""), "", "", new Prepaid(""), 0);

        assertNotNull(doctorClinics);
        assertEquals(2, doctorClinics.size());
        assertEquals(location.getLocationName(), doctorClinics.get(0).getClinic().getLocation().getLocationName());

    }

    @Test
    public void testGetFilteredDoctorsBySpecialty(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getFilteredDoctors(new Location(""), specialty, "", "", new Prepaid(""), 0);

        assertNotNull(doctorClinics);
        assertEquals(2, doctorClinics.size());
        assertEquals(specialty.getSpecialtyName(), doctorClinics.get(0).getDoctor().getSpecialty().getSpecialtyName());

    }

    @Test
    public void testGetFilteredDoctorsByFirstName(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getFilteredDoctors(new Location(""), new Specialty(""), doc.getFirstName(), "", new Prepaid(""), 0);

        assertNotNull(doctorClinics);
        assertEquals(1, doctorClinics.size());
        assertEquals(doc.getFirstName(), doctorClinics.get(0).getDoctor().getFirstName());

    }

    @Test
    public void testGetFilteredDoctorsByLastName(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getFilteredDoctors(new Location(""), new Specialty(""), "", doc.getLastName(), new Prepaid(""), 0);

        assertNotNull(doctorClinics);
        assertEquals(1, doctorClinics.size());
        assertEquals(doc.getLastName(), doctorClinics.get(0).getDoctor().getLastName());

    }

    @Test
    public void testGetFilteredDoctorsByConsultPrice(){
        List<DoctorClinic> doctorClinics = doctorClinicDao.getFilteredDoctors(new Location(""), new Specialty(""), "", "", new Prepaid(""), consultPrice);

        assertNotNull(doctorClinics);
        assertEquals(2, doctorClinics.size());
        assertTrue(consultPrice >= doctorClinics.get(0).getConsultPrice());

    }
}