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
import ar.edu.itba.paw.models.Skill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SkillJDBCDaoTest extends DaoTest {

	@Autowired
	private SkillDao skillDao;

	@Before
	public void setUp() {
		jdbcTemplate = new JdbcTemplate(ds);
		cleanUp();
		createResourcesNeeded();
	}

	@Test
	public void create() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "skills"));
	}

	@Test
	public void delete() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "skills"));
		skillDao.delete(skillDao.findByName("paw").getId());
		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "skills"));
	}

	@Test
	public void update() {
		Skill skill = skillDao.findByName("paw");
		skillDao.update(skill.getId(), "test");
		assertEquals("test", skillDao.find(skill.getId()).getName());
	}

	@Test
	public void count() {
		assertEquals(1, skillDao.count().longValue());
		skillDao.create("other1");
		skillDao.create("other2");
		assertEquals(3, skillDao.count().longValue());
	}

	@Test
	public void find() {
		Skill expected = skillDao.findByName("paw");
		Skill skill = skillDao.find(expected.getId());
		assertEquals(expected, skill);
	}

	@Test
	public void findByName() {
		Skill skill = skillDao.findByName("paw");
		assertEquals("paw", skill.getName());
	}

	@Test
	public void all() {
		List<Skill> list = skillDao.all();
		assertEquals(1, list.size());
		assertEquals("paw", list.get(0).getName());
	}
	
	private void createResourcesNeeded() {
		skillDao.create("paw");
	}

}
