package ar.edu.itba.paw.persistence;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
/*public class UserJDBCDaoTest {

	private static final String EMAIL = "test@test.com";
	private static final String FIRST_NAME = "test";
	private static final String LAST_NAME = "test";
	private static final String PASSWORD = "test";

    @Autowired
    private DataSource ds;

    @Autowired
    private UserDao userDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testCreate() {
        userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        User user = userDao.findByEmail(EMAIL);

        assertNotNull(user);
        assertEquals(FIRST_NAME, user.getFirstName());
		assertEquals(LAST_NAME, user.getLastName());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(PASSWORD, user.getPassword());

        assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
    }
}*/