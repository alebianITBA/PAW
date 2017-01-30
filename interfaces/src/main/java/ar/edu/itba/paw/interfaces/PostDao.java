package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;

public interface PostDao {

  Post create(String title, String description, User user);

  void delete(Long id);

  Post update(Long id, String title, String description);

  Long count();

  Post find(Long id);

  List<Post> all();

  List<Post> all(Integer page, Integer perPage);

  List<Post> userPosts(Long userId);

  List<Post> userPosts(Long userId, Integer page, Integer perPage);

}
