package ar.edu.itba.paw.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserSkillDao;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserSkillJDBCDaoTest extends DaoTest {

	@Autowired
	private UserSkillDao userSkillDao;

	@Autowired
	private SkillDao skillDao;

	@Autowired
	private UserDao userDao;

	private User user;

	@Before
	public void setUp() {
		jdbcTemplate = new JdbcTemplate(ds);
		cleanUp();
		createResourcesNeeded();
	}

	@Test
	public void create() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "user_skills"));
	}

	@Test
	public void removeUserSkill() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "user_skills"));
		userSkillDao.removeUserSkill(user.getId(), userSkillDao.userSkills(user.getId()).get(0).getId());
		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "user_skills"));
	}

	@Test
	public void userSkills() {
		List<Skill> userSkills = userSkillDao.userSkills(user.getId());
		assertEquals(1, userSkills.size());
		assertEquals("paw", userSkills.get(0).getName());
	}

	private void createResourcesNeeded() {
		skillDao.create("paw");
		userDao.create("test", "test", "test@test.com", "test");
		user = userDao.findByEmail("test@test.com");
		userSkillDao.create(user.getId(), skillDao.findByName("paw").getId());
	}

}
