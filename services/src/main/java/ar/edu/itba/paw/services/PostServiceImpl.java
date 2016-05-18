package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;
import ar.edu.itba.paw.models.User;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	@Transactional
	public void create(String title, String description, User user) {
		postDao.create(title, description, user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		postDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Long id, String title, String description) {
		postDao.update(id, title, description);
	}

	@Override
	@Transactional
	public Long count() {
		return postDao.count();
	}

	@Override
	@Transactional
	public Post find(Long id) {
		return postDao.find(id);
	}

	@Override
	@Transactional
	public List<Post> all() {
		return postDao.all();
	}

	@Override
	@Transactional
	public List<Post> all(Integer page, Integer perPage) {
		return postDao.all(page, perPage);
	}

	@Override
	@Transactional
	public List<Post> userPosts(Long userId) {
		return postDao.userPosts(userId);
	}

	@Override
	@Transactional
	public List<Post> userPosts(Long userId, Integer page, Integer perPage) {
		return postDao.userPosts(userId, page, perPage);
	}

}
