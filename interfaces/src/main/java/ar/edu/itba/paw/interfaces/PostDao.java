package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Post;

import java.util.List;

public interface PostDao {

  void create(String title, String description, Long userId);

  void delete(Long id);

  void update(Long id, String title, String description);

  Long count();

  Post find(Long id);

  List<Post> all();

  List<Post> all(Integer page, Integer perPage);

  List<Post> userPosts(Long userId);

  List<Post> userPosts(Long userId, Integer page, Integer perPage);

}
