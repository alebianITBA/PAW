package ar.edu.itba.paw.persistence;

import static org.junit.Assert.assertEquals;

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

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class JobOfferJDBCDaoTest extends DaoTest {

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private User jobOfferCreator;

	@Autowired
	private JobOfferDao jobOfferDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SkillDao skillDao;

//	@Before
//	public void setUp() {
//		jdbcTemplate = new JdbcTemplate(ds);
//		cleanUp();
//		createResourcesNeeded();
//	}
//
//	@Test
//	public void create() {
//		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_offers"));
//	}
//
//	@Test
//	public void delete() {
//		jobOfferDao.delete(jobOfferDao.userJobOffers(jobOfferCreator.getId()).get(0).getId());
//		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_offers"));
//	}

	@Test
	public void update() {
		JobOffer jobOffer = jobOfferDao.userJobOffers(jobOfferCreator.getId()).get(0);
		assertEquals(TITLE, jobOffer.getTitle());
		assertEquals(DESCRIPTION, jobOffer.getDescription());
		
		jobOfferDao.update(jobOffer.getId(), "hola", "chau");
		
		jobOffer = jobOfferDao.find(jobOffer.getId());
		assertEquals("hola", jobOffer.getTitle());
		assertEquals("chau", jobOffer.getDescription());
	}

//	@Test
//	public void count() {
//		//jobOfferDao.create(TITLE, DESCRIPTION, jobOfferCreator.getId());
//		assertEquals(2, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_offers"));
//	}

	@Test
	public void find() {
		JobOffer jobOffer = jobOfferDao.userJobOffers(jobOfferCreator.getId()).get(0);
		assertEquals(TITLE, jobOffer.getTitle());
		assertEquals(DESCRIPTION, jobOffer.getDescription());
		//assertEquals(jobOfferCreator.getId(), jobOffer.getUserId());
	}

	@Test
	public void all() {
		List<JobOffer> actual = jobOfferDao.all();
		assertEquals(1, actual.size());
		JobOffer first = actual.get(0);
		assertEquals(DESCRIPTION, first.getDescription());
		assertEquals(TITLE, first.getTitle());
		//assertEquals(jobOfferCreator.getId(), first.getUserId());

		actual = jobOfferDao.all(1, 1);
		assertEquals(1, actual.size());
		first = actual.get(0);
		assertEquals(DESCRIPTION, first.getDescription());
		assertEquals(TITLE, first.getTitle());
		//assertEquals(jobOfferCreator.getId(), first.getUserId());
	}

	@Test
	public void userJobOffers() {
		List<JobOffer> actual = jobOfferDao.userJobOffers(jobOfferCreator.getId());
		assertEquals(1, actual.size());
		JobOffer first = actual.get(0);
		assertEquals(DESCRIPTION, first.getDescription());
		assertEquals(TITLE, first.getTitle());
		//assertEquals(jobOfferCreator.getId(), first.getUserId());

		actual = jobOfferDao.userJobOffers(jobOfferCreator.getId(), 1, 1);
		assertEquals(1, actual.size());
		first = actual.get(0);
		assertEquals(DESCRIPTION, first.getDescription());
		assertEquals(TITLE, first.getTitle());
		//assertEquals(jobOfferCreator.getId(), first.getUserId());
	}

	@Test
	public void withSkills() {
		skillDao.create("first");
		Skill skill = skillDao.all().get(0);
		List<Skill> skillList = new LinkedList<Skill>();
		skillList.add(skill);
		//jobOfferSkillDao.create(jobOfferDao.userJobOffers(jobOfferCreator.getId()).get(0).getId(), skill.getId());
		
		List<JobOffer> actual = jobOfferDao.withSkills(skillList);
		assertEquals(1, actual.size());
		JobOffer first = actual.get(0);
		assertEquals(DESCRIPTION, first.getDescription());
		assertEquals(TITLE, first.getTitle());
		//assertEquals(jobOfferCreator.getId(), first.getUserId());

//		actual = jobOfferDao.withSkills(skillList, 1, 50);
//		assertEquals(1, actual.size());
//		first = actual.get(0);
//		assertEquals(DESCRIPTION, first.getDescription());
//		assertEquals(TITLE, first.getTitle());
//		assertEquals(jobOfferCreator.getId(), first.getUserId());
	}

	private void createResourcesNeeded() {
		userDao.create("pepe", "pepe", "pepe@pepe.com", "pepe");
		jobOfferCreator = userDao.findByEmail("pepe@pepe.com");

		//jobOfferDao.create(TITLE, DESCRIPTION, jobOfferCreator.getId());
	}

}
