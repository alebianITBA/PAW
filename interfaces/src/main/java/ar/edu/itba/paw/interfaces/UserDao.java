package ar.edu.itba.paw.interfaces;

import java.sql.SQLException;

import ar.edu.itba.paw.models.User;

public interface UserDao {

	public User create(String email, String password) throws SQLException;
	public void delete(Long id);
	public void update(String firstName, String lastName, String email, String password);
	public User getByEmail(String email) throws SQLException;
	public Long count();
	public User find(Long id);
	public void createTable();
}
