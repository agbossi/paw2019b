package ar.edu.itba.paw.persistence;
/*
import ar.edu.itba.paw.model.Location;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@Sql("classpath:schema.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class LocationDaoImplTest {

    private static final String name = "location";

    private static final String name2 = "location2";

    @Autowired
    DataSource ds;

    JdbcTemplate jdbcTemplate;

    @Autowired
    private LocationDaoImpl locationDao;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Test
    public void testCreate(){
        Location location = locationDao.createLocation(name2);

        assertNotNull(location);
        assertEquals(name2, location.getLocationName());

        assertEquals(2, JdbcTestUtils.countRowsInTable(jdbcTemplate, "locations"));

    }

    @Test
    public void testGetLocationByName(){
        Location location = locationDao.getLocationByName(name);

        assertNotNull(location);
        assertEquals(name, location.getLocationName());

    }

    @Test
    public void testGetLocations(){
        List<Location> locations = locationDao.getLocations();

        assertNotNull(locations);
        assertEquals(1, locations.size());

    }
}*/
