package ar.edu.itba.paw.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.edu.itba.paw.interfaces.UserDao;

public class UserServiceImplTest {
	private static final String USERNAME = "TEST";
	private UserServiceImpl userService;
	
	@Mock
	private UserDao userDao;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		userService = new UserServiceImpl();
		userService.setUserDao(userDao);
	}
	
	@Test
	public void testGetByUsername(){
		userService.getByUsername(USERNAME).getUsername();
	}
}
