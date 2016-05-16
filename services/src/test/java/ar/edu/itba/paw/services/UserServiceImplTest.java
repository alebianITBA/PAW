package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.UserDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {

  private UserServiceImpl userService;

  private static final String EMAIL = "test@test.com";
  private static final String FIRST_NAME = "test";
  private static final String LAST_NAME = "test";
  private static final String PASSWORD = "test";

  @Mock
  private UserDao userDao;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    userService = new UserServiceImpl();
    userService.setUserDao(userDao);

    userService.create(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD);
  }

  @Test
  public void testFindByEmail() {
    /*User user = userService.findByEmail(EMAIL);
    assertNotNull(user);
    assertEquals(FIRST_NAME, user.getFirstName());
    assertEquals(LAST_NAME, user.getLastName());
    assertEquals(EMAIL, user.getEmail());
    assertEquals(PASSWORD, user.getPassword());*/
  }
}
