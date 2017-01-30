package ar.edu.itba.paw.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
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

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class JobOfferHibernateDaoTest extends DaoTest {

  private static final String TABLE_NAME = "job_offers";
  private static final String TITLE = "test";
  private static final String DESCRIPTION = "test";
  private User creator;

  @Autowired
  private JobOfferDao jobOfferDao;

  @Autowired
  private UserDao userDao;
  
  @Autowired
  private SkillDao skillDao;

  @PersistenceContext
  private EntityManager em;

  @Before
  public void setUp() {
    cleanUp();
    if (creator == null) {
      creator = userDao.create("test", "creator", "test@creator.com", "123");
    }
  }

  @Test
  public void testCreate() {
    assertEquals(0, rowCount());

    JobOffer jobOffer = jobOfferDao.create(TITLE, DESCRIPTION, creator);

    assertEquals(1, rowCount());
    assertNotNull(jobOffer.getId());
    assertEquals(TITLE, jobOffer.getTitle());
    assertEquals(DESCRIPTION, jobOffer.getDescription());
    assertEquals(creator, jobOffer.getUser());
  }
  
  @Test
  public void testCreateWithSkills() {
    assertEquals(0, rowCount());
    Skill skill = skillDao.create("TEST");
    List<Skill> skills = new LinkedList<Skill>();
    skills.add(skill);

    JobOffer jobOffer = jobOfferDao.create(TITLE, DESCRIPTION, creator, skills);

    assertEquals(1, rowCount());
    assertNotNull(jobOffer.getId());
    assertEquals(TITLE, jobOffer.getTitle());
    assertEquals(DESCRIPTION, jobOffer.getDescription());
    assertEquals(creator, jobOffer.getUser());
    assertEquals(skills, jobOffer.getSkills());
  }

  @Test
  public void testDelete() {
    JobOffer jobOffer = jobOfferDao.create(TITLE, DESCRIPTION, creator);
    assertEquals(1, rowCount());

    jobOfferDao.delete(jobOffer.getId());

    assertEquals(0, rowCount());
  }

  @Test
  public void testUpdate() {
    JobOffer jobOffer = jobOfferDao.create(TITLE, DESCRIPTION, creator);
    String newTitle = TITLE + "2";
    String newDescription = DESCRIPTION + "2";

    JobOffer result = jobOfferDao.update(jobOffer.getId(), newTitle, newDescription);

    assertEquals(jobOffer.getId(), result.getId());
    assertEquals(newTitle, result.getTitle());
    assertEquals(newDescription, result.getDescription());

    JobOffer check = jobOfferDao.find(jobOffer.getId());
    assertEquals(check.getId(), result.getId());
    assertEquals(check, result);

    result = jobOfferDao.update(jobOffer.getId(), null, newDescription);
    assertEquals(newTitle, result.getTitle());
    result = jobOfferDao.update(jobOffer.getId(), "", newDescription);
    assertEquals(newTitle, result.getTitle());

    result = jobOfferDao.update(jobOffer.getId(), newTitle, null);
    assertEquals(newDescription, result.getDescription());
    result = jobOfferDao.update(jobOffer.getId(), newTitle, "");
    assertEquals(newDescription, result.getDescription());
  }

  @Test
  public void testCount() {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    assertEquals(2, jobOfferDao.count().intValue());
  }

  @Test
  public void testFind() {
    JobOffer jobOffer = jobOfferDao.create(TITLE, DESCRIPTION, creator);

    JobOffer found = jobOfferDao.find(jobOffer.getId());

    assertEquals(jobOffer, found);
  }

  @Test
  public void testAll() throws InterruptedException {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    List<JobOffer> result = jobOfferDao.all();

    assertEquals(2, result.size());
  }

  @Test
  public void testPaginatedAll() {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    List<JobOffer> result = jobOfferDao.all(0, 1);

    assertEquals(1, result.size());
  }
  
  @Test
  public void testUserJobOffers() {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    List<JobOffer> result = jobOfferDao.userJobOffers(creator.getId());

    assertEquals(2, result.size());
  }

  @Test
  public void testPaginatedUserJobOffers() {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    List<JobOffer> result = jobOfferDao.userJobOffers(creator.getId(), 0, 1);

    assertEquals(1, result.size());
  }

  @Test
  public void testWithSkills() {
  }

  @Test
  public void testPaginatedWithSkills() {
  }

  @Test
  public void testNotFromUser() {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    List<JobOffer> result = jobOfferDao.notFromUser(creator.getId());

    assertEquals(0, result.size());
  }

  @Test
  public void testPaginatedNotFromUser() {
    jobOfferDao.create(TITLE, DESCRIPTION, creator);
    jobOfferDao.create(TITLE, DESCRIPTION, creator);

    List<JobOffer> result = jobOfferDao.notFromUser(creator.getId(), 0, 1);

    assertEquals(0, result.size());
  }
  
  @Test
  public void notApplied() {
  }

  @Override
  protected String tableName() {
    return TABLE_NAME;
  }
}
