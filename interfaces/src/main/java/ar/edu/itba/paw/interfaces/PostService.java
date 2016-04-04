package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Post;

public interface PostService {

	void create(String title, String description, Long userId);

	void delete(Long id);

	void update(Long id, String title, String description);

	Long count();

	Post find(Long id);

	List<Post> all();

	List<Post> userPosts(Long userId);

}
