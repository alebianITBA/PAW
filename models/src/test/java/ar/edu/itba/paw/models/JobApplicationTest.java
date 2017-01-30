package ar.edu.itba.paw.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

public class JobApplicationTest {

  private final Long ID = 1L;
  private final String DESCRIPTION = "DESCRIPTION";
  private final User USER = new User(ID, "pepe", "pepe", "pepe@pepe.com", "pass");
  private final JobOffer JOB_OFFER = new JobOffer(ID, "titulo", DESCRIPTION, USER, new Date(), null);

  @Test
  public void validJobApplicationTest() {
    JobApplication application = new JobApplication(ID, DESCRIPTION, USER, JOB_OFFER, new Date());

    assertEquals(ID, application.getId());
    assertEquals(DESCRIPTION, application.getDescription());
    assertEquals(USER, application.getUser());
    assertEquals(JOB_OFFER, application.getJobOffer());
  }

  @Test
  public void jobApplicationToString() {
    JobApplication application = new JobApplication(ID, DESCRIPTION, USER, JOB_OFFER, new Date());

    assertEquals("[ JOB APPLICATION OF USER: 1 TO JOB OFFER: 1 ]", application.toString());
  }
  
  @Test
    public void jobApplicationHashCode() {
    JobApplication application = new JobApplication(ID, DESCRIPTION, USER, JOB_OFFER, new Date());
        
        assertEquals(1, application.hashCode());
    }

  @Test
  public void jobApplicationEquals() {
    JobApplication application = new JobApplication(ID, DESCRIPTION, USER, JOB_OFFER, new Date());
    JobApplication identicalApplication = new JobApplication(ID, DESCRIPTION,
        new User(ID, "pepe", "pepe", "pepe@pepe.com", "pass"),
        new JobOffer(ID, "titulo", DESCRIPTION, USER, new Date(), null), new Date());
    JobApplication noIdApplication = new JobApplication(null, DESCRIPTION, USER, JOB_OFFER, new Date());
    JobApplication noDescriptionApplication = new JobApplication(ID, null, USER, JOB_OFFER, new Date());
    JobApplication noUserApplication = new JobApplication(ID, DESCRIPTION, null, JOB_OFFER, new Date());
    JobApplication noOfferApplication = new JobApplication(ID, DESCRIPTION, USER, null, new Date());
    JobApplication noDateApplication = new JobApplication(ID, DESCRIPTION, USER, JOB_OFFER, null);

    assertEquals(application, application);
    assertEquals(application, identicalApplication);
    assertNotEquals(application, noIdApplication);
    assertNotEquals(application, noDescriptionApplication);
    assertNotEquals(application, noUserApplication);
    assertNotEquals(application, noOfferApplication);
    assertEquals(application, noDateApplication);
  }
}