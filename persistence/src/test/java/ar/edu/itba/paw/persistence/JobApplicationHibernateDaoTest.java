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

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class JobApplicationHibernateDaoTest extends DaoTest {

	private static final String TABLE_NAME = "job_applications";
	private static final String TITLE = "test";
	private static final String DESCRIPTION = "test";
	private User offerCreator;
	private User applicator;
	private JobOffer offer;

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private JobOfferDao jobOfferDao;

	@PersistenceContext
	private EntityManager em;

	@Before
	public void setUp() {
		cleanUp();
		if (offerCreator == null) {
			offerCreator = userDao.create("test", "creator", "test@creator.com", "123");
		}
		if (applicator == null) {
			applicator = userDao.create("test", "applicator", "test@applicator.com", "123");
		}
		if (offer == null) {
			offer = jobOfferDao.create(TITLE, DESCRIPTION, offerCreator);
		}
	}

	@Test
	public void testCreate() {
		assertEquals(0, rowCount());

		JobApplication application = jobApplicationDao.create(DESCRIPTION, applicator, offer);

		assertEquals(1, rowCount());
		assertNotNull(application.getId());
		assertEquals(DESCRIPTION, application.getDescription());
		assertEquals(applicator, application.getUser());
	}

	@Test
	public void testDelete() {
		JobApplication application = jobApplicationDao.create(DESCRIPTION, applicator, offer);
		assertEquals(1, rowCount());

		jobApplicationDao.delete(application.getId());

		assertEquals(0, rowCount());
	}

	@Test
	public void testUpdate() {
		JobApplication application = jobApplicationDao.create(DESCRIPTION, applicator, offer);
		String newDescription = DESCRIPTION + "2";

		JobApplication result = jobApplicationDao.update(application.getId(), newDescription);

		assertEquals(application.getId(), result.getId());
		assertEquals(newDescription, result.getDescription());

		JobApplication check = jobApplicationDao.find(application.getId());
		assertEquals(check.getId(), result.getId());
		assertEquals(check, result);

		result = jobApplicationDao.update(application.getId(), null);
		assertEquals(newDescription, result.getDescription());
		result = jobApplicationDao.update(application.getId(), "");
		assertEquals(newDescription, result.getDescription());
	}

	@Test
	public void testCount() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		assertEquals(1, jobApplicationDao.count().intValue());
	}

	@Test
	public void testFind() {
		JobApplication application = jobApplicationDao.create(DESCRIPTION, applicator, offer);

		JobApplication found = jobApplicationDao.find(application.getId());

		assertEquals(application, found);
	}

	@Test
	public void testAll() throws InterruptedException {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		List<JobApplication> result = jobApplicationDao.all();

		assertEquals(1, result.size());
	}

	@Test
	public void testPaginatedAll() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		List<JobApplication> result = jobApplicationDao.all(0, 1);

		assertEquals(1, result.size());
	}

	@Test
	public void testUserJobApplications() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		List<JobApplication> result = jobApplicationDao.userJobApplications(applicator.getId());

		assertEquals(1, result.size());
	}

	@Test
	public void testPaginatedUserJobApplications() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		List<JobApplication> result = jobApplicationDao.userJobApplications(applicator.getId(), 0, 1);

		assertEquals(1, result.size());
	}

	@Test
	public void testNotFromUser() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		List<JobApplication> result = jobApplicationDao.jobOfferApplications(offer.getId());

		assertEquals(1, result.size());
	}

	@Test
	public void testPaginatedNotFromUser() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		List<JobApplication> result = jobApplicationDao.jobOfferApplications(offer.getId(), 0, 1);

		assertEquals(1, result.size());
	}

	@Test
	public void removeOfferApplications() {
		jobApplicationDao.create(DESCRIPTION, applicator, offer);

		jobApplicationDao.removeJobOfferApplications(offer.getId());

		assertEquals(0, rowCount());
	}

	@Override
	protected String tableName() {
		return TABLE_NAME;
	}
}
