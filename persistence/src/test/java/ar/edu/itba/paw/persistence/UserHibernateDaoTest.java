package ar.edu.itba.paw.persistence;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserHibernateDaoTest extends DaoTest {
	
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
	@Transactional
	public void testCreate() {
		assertEquals(0, em.createNativeQuery("SELECT COUNT(*) FROM users;").getFirstResult());
		userDao.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		assertEquals(1, em.createNativeQuery("SELECT COUNT(*) FROM users;").getFirstResult());
	}
}
