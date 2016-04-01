package ar.edu.itba.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(String email, String password) {
		userDao.create(email, password);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public void update(String firstName, String lastName, String email, String password) {
		userDao.update(firstName, lastName, email, password);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public Long count() {
		return userDao.count();
	}

	public User find(Long id) {
		return userDao.find(id);
	}
}
