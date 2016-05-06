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

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class JobApplicationJDBCDaoTest extends DaoTest {

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private User jobOfferCreator;
	private User jobApplicator;
	private JobOffer offer;

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private JobOfferDao jobOfferDao;

	@Before
	public void setUp() {
		jdbcTemplate = new JdbcTemplate(ds);
		cleanUp();
		createResourcesNeeded();
	}

	@Test
	public void testCreate() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_applications"));
	}

	@Test
	public void testDelete() {
		jobApplicationDao.delete(jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0).getId());
		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "job_applications"));
	}

	@Test
	public void testUpdate() {
		JobApplication application = jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0);
		assertEquals(DESCRIPTION, application.getDescription());
		jobApplicationDao.update(application.getId(), "test");
		application = jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0);
		assertEquals("test", application.getDescription());
	}

	@Test
	public void testCount() {
		assertEquals(1, jobApplicationDao.count().longValue());
	}

	@Test
	public void testFind() {
		JobApplication application = jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0);
		JobApplication found = jobApplicationDao.find(application.getId());
		assertEquals(application, found);
	}

	@Test
	public void testAll() {
		List<JobApplication> expected = new LinkedList<JobApplication>();
		expected.add(jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0));
		
		List<JobApplication> actual = jobApplicationDao.all();
		assertEquals(expected, actual);
		
		actual = jobApplicationDao.all(1, 1);
		assertEquals(expected, actual);
	}

	@Test
	public void testUserJobApplications() {
		List<JobApplication> expected = new LinkedList<JobApplication>();
		expected.add(jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0));
		
		List<JobApplication> actual = jobApplicationDao.userJobApplications(jobApplicator.getId());
		assertEquals(expected, actual);
		
		actual = jobApplicationDao.userJobApplications(jobApplicator.getId(), 1, 1);
		assertEquals(expected, actual);
	}

	@Test
	public void testJobOfferApplications() {
		List<JobApplication> expected = new LinkedList<JobApplication>();
		expected.add(jobApplicationDao.userJobApplications(jobApplicator.getId()).get(0));
		
		List<JobApplication> actual = jobApplicationDao.jobOfferApplications(offer.getId());
		assertEquals(expected, actual);
		
		actual = jobApplicationDao.jobOfferApplications(offer.getId(), 1, 1);
		assertEquals(expected, actual);
	}

	private void createResourcesNeeded() {		
		userDao.create("pepe", "pepe", "pepe@pepe.com", "pepe");
		userDao.create("tito", "tito", "tito@pepe.com", "tito");
		
		jobOfferCreator = userDao.findByEmail("pepe@pepe.com");
		jobApplicator = userDao.findByEmail("tito@pepe.com");
		
		jobOfferDao.create(TITLE, DESCRIPTION, jobOfferCreator.getId());
		
		offer = jobOfferDao.userJobOffers(jobOfferCreator.getId()).get(0);
		
		jobApplicationDao.create(DESCRIPTION, jobApplicator.getId(), offer.getId());
	}

}
