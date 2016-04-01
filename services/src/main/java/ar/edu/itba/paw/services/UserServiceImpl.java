package ar.edu.itba.paw.services;

import java.sql.SQLException;

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

	public User create(String email, String password) throws SQLException {
		return userDao.create(email, password);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public void update(String firstName, String lastName, String email, String password) {
		userDao.update(firstName, lastName, email, password);
	}

	public User getByEmail(String email) throws SQLException {
		return userDao.findByEmail(email);
	}

	public Long count() {
		return userDao.count();
	}

	public User find(Long id) throws SQLException {
		return userDao.find(id);
	}

	public void createTable() {
		userDao.createTable();
	}
}
