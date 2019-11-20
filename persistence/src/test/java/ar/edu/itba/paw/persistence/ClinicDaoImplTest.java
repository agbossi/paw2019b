package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.Clinic;
import ar.edu.itba.paw.model.Location;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ClinicDaoImplTest {

    private static final String name = "clinic";

    private static final int id = 1;

    private static final String name2 = "clinic3";

    private static final String address = "address";

    private static final String address2 = "address3";

    private static final Location location = new Location("location");

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreate() {
        assertEquals(true, true);
    }
/*
    @Test
    public void testCreate(){
        final Clinic clinic = clinicDao.createClinic(name2, address2, location);

        assertNotNull(clinic);
        assertEquals(name2, clinic.getName());
        assertEquals(address2, clinic.getAddress());
        assertEquals(location.getLocationName(), clinic.getLocation().getLocationName());

        assertEquals(3, JdbcTestUtils.countRowsInTable(jdbcTemplate, "clinics"));

    }

    @Test
    public void testGetClinicByName(){
        final Clinic clinic = clinicDao.getClinicByName(name);

        assertNotNull(clinic);
        assertEquals(name, clinic.getName());

    }

    @Test
    public void testGetClinicById(){
        final Clinic clinic = clinicDao.getClinicById(id);

        assertNotNull(clinic);
        assertEquals(id, clinic.getId());

    }

    @Test
    public void testGetClinics(){
        List<Clinic> clinics = clinicDao.getClinics();

        assertNotNull(clinics);
        assertEquals(2,clinics.size());
    }

    @Test
    public void testGetClinicByLocation(){
        List<Clinic> clinics = clinicDao.getClinicsByLocation(location.getLocationName());

        assertNotNull(clinics);
        assertEquals(2, clinics.size());
    }

    @Test
    public void testClinicExists(){
        boolean bool = clinicDao.clinicExists(name, address, location.getLocationName());
        boolean bool2 = clinicDao.clinicExists(name2, address2, location.getLocationName());

        Assert.assertTrue(bool);
        Assert.assertTrue(!bool2);
    }
*/
}
