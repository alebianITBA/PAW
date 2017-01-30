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

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class PostHibernateDaoTest extends DaoTest {

  private static final String TABLE_NAME = "posts";
  private static final String TITLE = "test";
  private static final String DESCRIPTION = "test";
  private User creator;

  @Autowired
  private PostDao postDao;

  @Autowired
  private UserDao userDao;

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

    Post post = postDao.create(TITLE, DESCRIPTION, creator);

    assertEquals(1, rowCount());
    assertNotNull(post.getId());
    assertEquals(TITLE, post.getTitle());
    assertEquals(DESCRIPTION, post.getDescription());
    assertEquals(creator, post.getUser());
  }

  @Test
  public void testDelete() {
    Post post = postDao.create(TITLE, DESCRIPTION, creator);
    assertEquals(1, rowCount());

    postDao.delete(post.getId());

    assertEquals(0, rowCount());
  }

  @Test
  public void testUpdate() {
    Post post = postDao.create(TITLE, DESCRIPTION, creator);
    String newTitle = TITLE + "2";
    String newDescription = DESCRIPTION + "2";

    Post result = postDao.update(post.getId(), newTitle, newDescription);

    assertEquals(post.getId(), result.getId());
    assertEquals(newTitle, result.getTitle());
    assertEquals(newDescription, result.getDescription());

    Post check = postDao.find(post.getId());
    assertEquals(check.getId(), result.getId());
    assertEquals(check, result);

    result = postDao.update(post.getId(), null, newDescription);
    assertEquals(newTitle, result.getTitle());
    result = postDao.update(post.getId(), "", newDescription);
    assertEquals(newTitle, result.getTitle());

    result = postDao.update(post.getId(), newTitle, null);
    assertEquals(newDescription, result.getDescription());
    result = postDao.update(post.getId(), newTitle, "");
    assertEquals(newDescription, result.getDescription());
  }

  @Test
  public void testCount() {
    postDao.create(TITLE, DESCRIPTION, creator);
    postDao.create(TITLE, DESCRIPTION, creator);

    assertEquals(2, postDao.count().intValue());
  }

  @Test
  public void testFind() {
    Post post = postDao.create(TITLE, DESCRIPTION, creator);

    Post found = postDao.find(post.getId());

    assertEquals(post, found);
  }

  @Test
  public void testAll() throws InterruptedException {
    postDao.create(TITLE, DESCRIPTION, creator);
    postDao.create(TITLE, DESCRIPTION, creator);

    List<Post> result = postDao.all();

    assertEquals(2, result.size());
  }

  @Test
  public void testPaginatedAll() {
    postDao.create(TITLE, DESCRIPTION, creator);
    postDao.create(TITLE, DESCRIPTION, creator);

    List<Post> result = postDao.all(0, 1);

    assertEquals(1, result.size());
  }
  
  @Test
  public void testUserPosts() throws InterruptedException {
    postDao.create(TITLE, DESCRIPTION, creator);
    postDao.create(TITLE, DESCRIPTION, creator);

    List<Post> result = postDao.userPosts(creator.getId());

    assertEquals(2, result.size());
  }

  @Test
  public void testPaginatedUserPosts() {
    postDao.create(TITLE, DESCRIPTION, creator);
    postDao.create(TITLE, DESCRIPTION, creator);

    List<Post> result = postDao.userPosts(creator.getId(), 0, 1);

    assertEquals(1, result.size());
  }

  @Override
  protected String tableName() {
    return TABLE_NAME;
  }
}
