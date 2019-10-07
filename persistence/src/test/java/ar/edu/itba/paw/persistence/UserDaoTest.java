package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)

public class UserDaoTest {
    private static final String FIRST_NAME = "Carlos";
    private static final String LAST_NAME = "Perez";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "Email";

    @Autowired
    private DataSource ds;
    @Autowired
    private UserDaoImpl userDaoImpl;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testCreate() {
        final User user = userDaoImpl.createUser(FIRST_NAME, LAST_NAME, PASSWORD, EMAIL);

        Assert.assertNotNull(user);
        Assert.assertEquals(FIRST_NAME, user.getFirstName());
        Assert.assertEquals(FIRST_NAME, user.getLastName());
        Assert.assertEquals(PASSWORD, user.getPassword());
        Assert.assertEquals(EMAIL, user.getEmail());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }
}