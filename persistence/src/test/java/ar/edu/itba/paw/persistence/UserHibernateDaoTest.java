package ar.edu.itba.paw.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class UserHibernateDaoTest extends DaoTest {

	private static final String TABLE_NAME = "users";
	private static final String EMAIL = "test@test.com";
	private static final String FIRST_NAME = "test";
	private static final String LAST_NAME = "test";
	private static final String PASSWORD = "test";

	@Autowired
	private UserDao userDao;

	@PersistenceContext
	private EntityManager em;

	@Before
	public void setUp() {
		cleanUp();
	}

	@Test
	public void testCreate() {
		assertEquals(0, rowCount());

		User user = userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);

		assertEquals(1, rowCount());
		assertNotNull(user.getId());
		assertEquals(EMAIL, user.getEmail());
		assertEquals(FIRST_NAME, user.getFirstName());
		assertEquals(LAST_NAME, user.getLastName());
		assertEquals(PASSWORD, user.getPassword());
	}

	@Test
	public void testDelete() {
		User user = userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		assertEquals(1, rowCount());

		userDao.delete(user.getId());

		assertEquals(0, rowCount());
	}

	@Test
	public void testUpdate() {
		User user = userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		String newFirstName = FIRST_NAME + "2";
		String newLastName = LAST_NAME + "2";
		String newEmail = EMAIL + "2";
		String newPassword = PASSWORD + "2";

		User result = userDao.update(user.getId(), newFirstName, newLastName, newEmail, newPassword);

		assertEquals(user.getId(), result.getId());
		assertEquals(newFirstName, result.getFirstName());
		assertEquals(newLastName, result.getLastName());
		assertEquals(newEmail, result.getEmail());
		assertEquals(newPassword, result.getPassword());

		User check = userDao.findByEmail(newEmail);
		assertEquals(check.getId(), result.getId());
		assertEquals(check, result);

		result = userDao.update(user.getId(), null, newLastName, newEmail, newPassword);
		assertEquals(newFirstName, result.getFirstName());
		result = userDao.update(user.getId(), "", newLastName, newEmail, newPassword);
		assertEquals(newFirstName, result.getFirstName());

		result = userDao.update(user.getId(), newFirstName, null, newEmail, newPassword);
		assertEquals(newLastName, result.getLastName());
		result = userDao.update(user.getId(), newFirstName, "", newEmail, newPassword);
		assertEquals(newLastName, result.getLastName());

		result = userDao.update(user.getId(), newFirstName, newLastName, null, newPassword);
		assertEquals(newEmail, result.getEmail());
		result = userDao.update(user.getId(), newFirstName, newLastName, "", newPassword);
		assertEquals(newEmail, result.getEmail());

		result = userDao.update(user.getId(), newFirstName, newLastName, newEmail, null);
		assertEquals(newPassword, result.getPassword());
		result = userDao.update(user.getId(), newFirstName, newLastName, newEmail, "");
		assertEquals(newPassword, result.getPassword());
	}

	@Test
	public void testCount() {
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL + "2", PASSWORD);

		assertEquals(2, userDao.count().intValue());
	}

	@Test
	public void testFind() {
		User user = userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);

		User found = userDao.find(user.getId());

		assertEquals(user, found);
	}

	@Test
	public void testFindByEmail() {
		User user = userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);

		User found = userDao.findByEmail(user.getEmail());

		assertEquals(user, found);
		assertEquals(user.getId(), found.getId());
	}

	@Test
	public void testAll() {
		User user1 = userDao.create(FIRST_NAME, LAST_NAME, "A" + EMAIL, PASSWORD);
		User user2 = userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);

		List<User> result = userDao.all();

		assertEquals(2, result.size());

		assertEquals(user1, result.get(0));
		assertEquals(user1.getId(), result.get(0).getId());
		assertEquals(user2, result.get(1));
		assertEquals(user2.getId(), result.get(1).getId());
	}

	@Test
	public void testPaginatedAll() {
		User user1 = userDao.create(FIRST_NAME, LAST_NAME, "A" + EMAIL, PASSWORD);
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);

		List<User> result = userDao.all(0, 1);

		assertEquals(1, result.size());

		assertEquals(user1, result.get(0));
		assertEquals(user1.getId(), result.get(0).getId());
	}

	@Override
	protected String tableName() {
		return TABLE_NAME;
	}
}
