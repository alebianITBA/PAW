package ar.edu.itba.paw.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserJDBCDaoTest extends DaoTest {

	private static final String EMAIL = "test@test.com";
	private static final String FIRST_NAME = "test";
	private static final String LAST_NAME = "test";
	private static final String PASSWORD = "test";

	@Autowired
	private UserDao userDao;

//	@Before
//	public void setUp() {
//		jdbcTemplate = new JdbcTemplate(ds);
//		cleanUp();
//	}
//
//	@Test
//	public void testCreate() {
//		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
//		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
//	}
//
//	@Test
//	public void testDelete() {
//		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
//		User user = userDao.findByEmail(EMAIL);
//		assertNotNull(user);
//
//		userDao.delete(user.getId());
//		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "users"));
//	}

	@Test
	public void testUpdate() {
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		User user = userDao.findByEmail(EMAIL);
		assertNotNull(user);
		// First let's test that we don't need all the parameters
		userDao.update(user.getId(), "hola", null, null, null);
		user = userDao.findByEmail(EMAIL);
		assertEquals("hola", user.getFirstName());
		assertEquals(LAST_NAME, user.getLastName());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(PASSWORD, user.getPassword());
		// Test changing everything
		userDao.update(user.getId(), "chau", "pepe", "pepe@pepe.com", "pepon");
		user = userDao.findByEmail("pepe@pepe.com");
		assertEquals("chau", user.getFirstName());
		assertEquals("pepe", user.getLastName());
		assertEquals("pepe@pepe.com", user.getEmail());
		assertEquals("pepon", user.getPassword());
	}

	@Test
	public void testFindByEmail() {
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		User user = userDao.findByEmail(EMAIL);

		assertNotNull(user);
		assertEquals(FIRST_NAME, user.getFirstName());
		assertEquals(LAST_NAME, user.getLastName());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(PASSWORD, user.getPassword());
	}

	@Test
	public void testCount() {
		userDao.create("asd", "asd", "asd1@asd.com", "asd");
		assertEquals(1L, userDao.count().longValue());
		userDao.create("asd", "asd", "asd2@asd.com", "asd");
		userDao.create("asd", "asd", "asd3@asd.com", "asd");
		assertEquals(3L, userDao.count().longValue());
	}

	@Test
	public void testFind() {
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		User user_by_email = userDao.findByEmail(EMAIL);
		User user = userDao.find(user_by_email.getId());

		assertNotNull(user);
		assertEquals(user_by_email, user);
		assertEquals(FIRST_NAME, user.getFirstName());
		assertEquals(LAST_NAME, user.getLastName());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(PASSWORD, user.getPassword());
	}

	@Test
	public void testAll() {
		userDao.create("asd", "asd", "asd1@asd.com", "asd");
		userDao.create("asd", "asd", "asd2@asd.com", "asd");
		userDao.create("asd", "asd", "asd3@asd.com", "asd");
		User user1 = userDao.findByEmail("asd1@asd.com");
		User user2 = userDao.findByEmail("asd2@asd.com");
		User user3 = userDao.findByEmail("asd3@asd.com");
		List<User> expected = new LinkedList<User>();
		expected.add(user1);
		expected.add(user2);
		expected.add(user3);
		assertEquals(expected, userDao.all());
		// Test the paginated version
		expected = new LinkedList<User>();
		expected.add(user3);
		assertEquals(expected, userDao.all(1, 1));
	}

}