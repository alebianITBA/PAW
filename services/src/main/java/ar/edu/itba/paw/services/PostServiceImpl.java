package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostDao postDao;

  @Autowired
  private UserService userService;

  public void setPostDao(PostDao postDao) {
    this.postDao = postDao;
  }

  @Override
  public void create(String title, String description, Long userId) {
    postDao.create(title, description, userId);
  }

  @Override
  public void delete(Long id) {
    postDao.delete(id);
  }

  @Override
  public void update(Long id, String title, String description) {
    postDao.update(id, title, description);
  }

  @Override
  public Long count() {
    return postDao.count();
  }

  @Override
  public Post find(Long id) {
    Post post = postDao.find(id);
    addUserToPost(post);
    return post;
  }

  @Override
  public List<Post> all() {
    List<Post> posts = postDao.all();
    for (Post post : posts) {
      addUserToPost(post);
    }
    return posts;
  }

  @Override
  public List<Post> all(Integer page, Integer perPage) {
    List<Post> posts = postDao.all(page, perPage);
    for (Post post : posts) {
      addUserToPost(post);
    }
    return posts;
  }

  @Override
  public List<Post> userPosts(Long userId) {
    return postDao.userPosts(userId);
  }

  @Override
  public List<Post> userPosts(Long userId, Integer page, Integer perPage) {
    return postDao.userPosts(userId, page, perPage);
  }

  private void addUserToPost(Post post) {
    post.setUser(userService.find(post.getUserId()));
  }
}
