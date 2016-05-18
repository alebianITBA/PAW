package ar.edu.itba.paw.models;

import static org.junit.Assert.*;

import org.junit.*;

public class UserTest {
	
	private final Long ID = 1L;
	private final String FIRST_NAME = "test_first_name";
	private final String LAST_NAME = "test_last_name";
	private final String EMAIL = "test@test.com";
	private final String PASSWORD = "test_password";
	
	@Test
    public void validUserTest() {
        User user = new User(ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        
        assertEquals(ID, user.getId());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
    }
	
	@Test
    public void userToString() {
        User user = new User(ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
        
        assertEquals("[ test_first_name test_last_name (email: test@test.com, id: 1) ]", user.toString());
    }
	
	@Test
	public void userEquals() {
		User fullUser = new User(ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		User differentUser = new User(ID, LAST_NAME, FIRST_NAME, EMAIL, PASSWORD);
		User noIdUser = new User(null, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		User noFirstNameUser = new User(ID, null, LAST_NAME, EMAIL, PASSWORD);
		User noLastNameUser = new User(ID, FIRST_NAME, null, EMAIL, PASSWORD);
		User noEmailUser = new User(ID, FIRST_NAME, LAST_NAME, null, PASSWORD);
		User noPasswordUser = new User(ID, FIRST_NAME, LAST_NAME, EMAIL, null);
		User identicalUser = new User(ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
		
		assertEquals(fullUser, fullUser);
		assertEquals(fullUser, identicalUser);
		assertNotEquals(fullUser, differentUser);
		assertNotEquals(fullUser, noIdUser);
		assertNotEquals(fullUser, noFirstNameUser);
		assertNotEquals(fullUser, noLastNameUser);
		assertNotEquals(fullUser, noEmailUser);
		assertNotEquals(fullUser, noPasswordUser);
	}
}
