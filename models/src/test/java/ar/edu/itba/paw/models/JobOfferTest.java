package ar.edu.itba.paw.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

public class JobOfferTest {
	
	private final Long ID = 1L;
	private final String TITLE = "TITLE";
	private final String DESCRIPTION = "DESCRIPTION";
	private final User USER = new User(ID, "pepe", "pepe", "pepe@pepe.com", "pass");
	
	@Test
    public void validJobOfferTest() {
        JobOffer offer = new JobOffer(ID, TITLE, DESCRIPTION, USER, new Date());
        
        assertEquals(ID, offer.getId());
        assertEquals(TITLE, offer.getTitle());
        assertEquals(DESCRIPTION, offer.getDescription());
        assertEquals(USER, offer.getUser());
    }
	
	@Test
    public void jobOfferToString() {
		JobOffer offer = new JobOffer(TITLE, DESCRIPTION, USER, new Date());
        
        assertEquals("[ JOB OFFER: TITLE OF USER: 1 ]", offer.toString());
    }
	
	@Test
    public void jobOfferHashCode() {
		JobOffer offer = new JobOffer(ID, TITLE, DESCRIPTION, USER, new Date());
        
        assertEquals(1, offer.hashCode());
    }
	
	@Test
	public void jobOfferEquals() {
		JobOffer offer = new JobOffer(ID, TITLE, DESCRIPTION, USER, new Date());
		JobOffer noTitleOffer = new JobOffer(ID, null, DESCRIPTION, USER, new Date());
		JobOffer noDescriptionOffer = new JobOffer(ID, TITLE, null, USER, new Date());
		JobOffer noUserOffer = new JobOffer(ID, TITLE, DESCRIPTION, null, new Date());
		JobOffer noDateOffer = new JobOffer(ID, TITLE, DESCRIPTION, USER, null);
		JobOffer otherOffer = new JobOffer(ID, "asd", "asd", USER, new Date());
		JobOffer identicalOffer = new JobOffer(ID, TITLE, DESCRIPTION, new User(ID, "pepe", "pepe", "pepe@pepe.com", "pass"), new Date());
		
		assertEquals(offer, offer);
		assertEquals(offer, identicalOffer);
		assertNotEquals(offer, noTitleOffer);
		assertNotEquals(offer, noDescriptionOffer);
		assertNotEquals(offer, noUserOffer);
		assertEquals(offer, noDateOffer);
		assertNotEquals(offer, otherOffer);
	}
}