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

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PostJDBCDaoTest extends DaoTest {

	private static String TITLE = "test";
	private static String DESCRIPTION = "test";

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	private User creator;

	@Before
	public void setUp() {
		jdbcTemplate = new JdbcTemplate(ds);
		cleanUp();
		createResourcesNeeded();
	}

	@Test
	public void create() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
	}

	@Test
	public void delete() {
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
		postDao.delete(postDao.userPosts(creator.getId()).get(0).getId());
		assertEquals(0, JdbcTestUtils.countRowsInTable(jdbcTemplate, "posts"));
	}

	@Test
	public void update() {
		Post post = postDao.userPosts(creator.getId()).get(0);
		assertEquals(TITLE, post.getTitle());
		assertEquals(DESCRIPTION, post.getDescription());
		
		postDao.update(post.getId(), "titulo", "desc");
		post = postDao.userPosts(creator.getId()).get(0);
		assertEquals("titulo", post.getTitle());
		assertEquals("desc", post.getDescription());
	}

	@Test
	public void count() {
		assertEquals(1, postDao.count().longValue());
		postDao.create(TITLE, DESCRIPTION, creator.getId());
		postDao.create(TITLE, DESCRIPTION, creator.getId());
		assertEquals(3, postDao.count().longValue());
	}

	@Test
	public void find() {
		Post expected = postDao.userPosts(creator.getId()).get(0);
		assertEquals(expected, postDao.find(expected.getId()));
	}

	@Test
	public void all() {
		List<Post> list = postDao.all();
		assertEquals(1, list.size());
		assertEquals(TITLE, list.get(0).getTitle());
		assertEquals(DESCRIPTION, list.get(0).getDescription());
		assertEquals(creator.getId(), list.get(0).getUserId());

		list = postDao.all(1, 1);
		assertEquals(1, list.size());
		assertEquals(TITLE, list.get(0).getTitle());
		assertEquals(DESCRIPTION, list.get(0).getDescription());
		assertEquals(creator.getId(), list.get(0).getUserId());
	}

	@Test
	public void userPosts() {
		List<Post> list = postDao.userPosts(creator.getId());
		assertEquals(1, list.size());
		assertEquals(TITLE, list.get(0).getTitle());
		assertEquals(DESCRIPTION, list.get(0).getDescription());
		assertEquals(creator.getId(), list.get(0).getUserId());
		
		list = postDao.userPosts(creator.getId(), 1, 1);
		assertEquals(1, list.size());
		assertEquals(TITLE, list.get(0).getTitle());
		assertEquals(DESCRIPTION, list.get(0).getDescription());
		assertEquals(creator.getId(), list.get(0).getUserId());
	}

	private void createResourcesNeeded() {
		userDao.create("test", "test", "test@test.com", "test");
		creator = userDao.findByEmail("test@test.com");

		postDao.create(TITLE, DESCRIPTION, creator.getId());
	}

}
