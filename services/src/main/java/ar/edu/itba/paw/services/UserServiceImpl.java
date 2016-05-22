package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User create(String firstName, String lastName, String email, String password) {
		return userDao.create(firstName, lastName, email, password);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public User update(Long id, String firstName, String lastName, String email, String password) {
		return userDao.update(id, firstName, lastName, email, password);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public Long count() {
		return userDao.count();
	}

	@Override
	public User find(Long id) {
		return userDao.find(id);
	}

	@Override
	public List<User> all() {
		return userDao.all();
	}

	@Override
	public List<User> all(Integer page, Integer perPage) {
		return userDao.all(page, perPage);
	}

}
