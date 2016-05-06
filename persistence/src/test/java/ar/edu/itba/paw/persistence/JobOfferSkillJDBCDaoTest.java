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

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.JobOfferSkillDao;
import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class JobOfferSkillJDBCDaoTest extends DaoTest {

	@Autowired
	private JobOfferSkillDao jobOfferSkillDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobOfferDao jobOfferDao;
	
	private User creator;
	private JobOffer offer;
	private Skill skill;

	@Before
	public void setUp() {
		jdbcTemplate = new JdbcTemplate(ds);
		cleanUp();
		createResourcesNeeded();
	}

	@Test
	public void create() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_offer_skills"));
	}

	@Test
	public void removeJobOfferSkill() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_offer_skills"));
		jobOfferSkillDao.removeJobOfferSkill(offer.getId(), skill.getId());
		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_offer_skills"));
	}

	@Test
	public void jobOfferSkills() {
		List<Skill> offerSkills = jobOfferSkillDao.jobOfferSkills(offer.getId());
		assertEquals(1, offerSkills.size());
		assertEquals("paw", offerSkills.get(0).getName());
	}
	
	private void createResourcesNeeded() {
		skillDao.create("paw");
		userDao.create("test", "test", "test@test.com", "test");
		creator = userDao.findByEmail("test@test.com");
		jobOfferDao.create("test", "test", creator.getId());
		offer = jobOfferDao.userJobOffers(creator.getId()).get(0);
		skill = skillDao.findByName("paw");
		
		jobOfferSkillDao.create(offer.getId(), skill.getId());
	}

}
