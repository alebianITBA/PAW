package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	public void create(String title, String description, Long userId) {
		postDao.create(title, description, userId);
	}

	public void delete(Long id) {
		postDao.delete(id);
	}

	public void update(Long id, String title, String description) {
		postDao.update(id, title, description);
	}

	public Long count() {
		return postDao.count();
	}

	public Post find(Long id) {
		return postDao.find(id);
	}

	public List<Post> all() {
		return postDao.all();
	}

	public List<Post> userPosts(Long userId) {
		return postDao.userPosts(userId);
	}
}
