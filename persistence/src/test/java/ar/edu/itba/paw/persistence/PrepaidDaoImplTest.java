package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.Prepaid;
import ar.edu.itba.paw.model.Specialty;
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
public class PrepaidDaoImplTest {

    private static final String name = "prepaid";

    private static final String name2 = "prepaid3";

    @Autowired
    DataSource ds;

    JdbcTemplate jdbcTemplate;

    @Autowired
    private PrepaidDaoImpl prepaidDao;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Test
    public void testCreate(){
        Prepaid prepaid = prepaidDao.createPrepaid(name2);

        assertNotNull(prepaid);
        assertEquals(name2, prepaid.getName());

        assertEquals(3, JdbcTestUtils.countRowsInTable(jdbcTemplate, "prepaids"));

    }

    @Test
    public void testGetSpecialtyByName(){
        Prepaid prepaid = prepaidDao.getPrepaidByName(name);

        assertNotNull(prepaid);
        assertEquals(name, prepaid.getName());

    }

    @Test
    public void testGetSpecialties(){
        List<Prepaid> prepaids = prepaidDao.getPrepaids();

        assertNotNull(prepaids);
        assertEquals(2, prepaids.size());

    }

}



