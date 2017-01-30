package ar.edu.itba.paw.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

public class PostTest {
  
  private final Long ID = 1L;
  private final String TITLE = "TITLE";
  private final String DESCRIPTION = "DESCRIPTION";
  private final User USER = new User(ID, "pepe", "pepe", "pepe@pepe.com", "pass");
  
  @Test
    public void validPostTest() {
        Post post = new Post(ID, TITLE, DESCRIPTION, USER, new Date());
        
        assertEquals(ID, post.getId());
        assertEquals(TITLE, post.getTitle());
        assertEquals(DESCRIPTION, post.getDescription());
        assertEquals(USER, post.getUser());
    }
  
  @Test
    public void postToString() {
    Post post = new Post(TITLE, DESCRIPTION, USER, new Date());
        
        assertEquals("[ POST: TITLE OF USER: 1 ]", post.toString());
    }
  
  @Test
    public void postHashCode() {
    Post post = new Post(ID, TITLE, DESCRIPTION, USER, new Date());
        
        assertEquals(1, post.hashCode());
    }
  
  @Test
  public void postEquals() {
    Post post = new Post(ID, TITLE, DESCRIPTION, USER, new Date());
    Post noIdPost = new Post(null, TITLE, DESCRIPTION, USER, new Date());
    Post noTitlePost = new Post(ID, null, DESCRIPTION, USER, new Date());
    Post noDescriptionPost = new Post(ID, TITLE, null, USER, new Date());
    Post noUserPost = new Post(ID, TITLE, DESCRIPTION, null, new Date());
    Post noDatePost = new Post(ID, TITLE, DESCRIPTION, USER, null);
    Post otherPost = new Post(ID, "asd", "asd", USER, new Date());
    Post identicalPost = new Post(ID, TITLE, DESCRIPTION, new User(ID, "pepe", "pepe", "pepe@pepe.com", "pass"), new Date());
    
    assertEquals(post, post);
    assertEquals(post, identicalPost);
    assertNotEquals(post, noIdPost);
    assertNotEquals(post, noTitlePost);
    assertNotEquals(post, noDescriptionPost);
    assertNotEquals(post, noUserPost);
    assertEquals(post, noDatePost);
    assertNotEquals(post, otherPost);
  }
}