package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserDao {

	User create(String username, String password);
	User getByUsername(String username);
}
