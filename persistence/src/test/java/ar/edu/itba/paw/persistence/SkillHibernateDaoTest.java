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

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.models.Skill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class SkillHibernateDaoTest extends DaoTest {

	private static final String TABLE_NAME = "skills";
	private static final String NAME = "TEST";

	@Autowired
	private SkillDao skillDao;

	@PersistenceContext
	private EntityManager em;

	@Before
	public void setUp() {
		cleanUp();
	}

	@Test
	public void testCreate() {
		assertEquals(0, rowCount());

		Skill skill = skillDao.create(NAME);

		assertEquals(1, rowCount());

		assertNotNull(skill.getId());
		assertEquals(NAME, skill.getName());
	}

	@Test
	public void testDelete() {
		Skill skill = skillDao.create(NAME);
		assertEquals(1, rowCount());

		skillDao.delete(skill.getId());

		assertEquals(0, rowCount());
	}

	@Test
	public void testUpdate() {
		Skill skill = skillDao.create(NAME);
		String newName = NAME + "2";

		Skill result = skillDao.update(skill.getId(), newName);

		assertEquals(newName, result.getName());

		Skill check = skillDao.findByName(newName);
		assertEquals(check.getId(), result.getId());
		assertEquals(check, result);
		
		result = skillDao.update(skill.getId(), null);
		assertEquals(newName, result.getName());
		result = skillDao.update(skill.getId(), "");
		assertEquals(newName, result.getName());
	}

	@Test
	public void testCount() {
		skillDao.create(NAME);
		skillDao.create(NAME + "TEST");

		assertEquals(2, skillDao.count().intValue());
	}

	@Test
	public void testFind() {
		Skill skill = skillDao.create(NAME);

		Skill found = skillDao.find(skill.getId());

		assertEquals(skill, found);
	}

	@Test
	public void testFindByName() {
		Skill skill = skillDao.create(NAME);

		Skill found = skillDao.findByName(skill.getName());

		assertEquals(skill, found);
		assertEquals(skill.getId(), found.getId());
	}

	@Test
	public void testAll() {
		Skill skill1 = skillDao.create("A " + NAME);
		Skill skill2 = skillDao.create(NAME);

		List<Skill> result = skillDao.all();

		assertEquals(2, result.size());

		assertEquals(skill1, result.get(0));
		assertEquals(skill1.getId(), result.get(0).getId());
		assertEquals(skill2, result.get(1));
		assertEquals(skill2.getId(), result.get(1).getId());
	}

	@Override
	protected String tableName() {
		return TABLE_NAME;
	}
}
