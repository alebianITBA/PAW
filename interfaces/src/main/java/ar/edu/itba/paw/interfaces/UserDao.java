package ar.edu.itba.paw.interfaces;

import java.sql.SQLException;

import ar.edu.itba.paw.models.User;

public interface UserDao {

	public User create(String email, String password) throws SQLException;
	public void delete(Long id);
	public void update(String firstName, String lastName, String email, String password);
	public User findByEmail(String email) throws SQLException;
	public Long count() throws SQLException;
	public User find(Long id) throws SQLException;
	public void createTable();
}
