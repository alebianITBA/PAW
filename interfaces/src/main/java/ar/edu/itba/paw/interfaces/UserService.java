package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserService {

	User create(String username, String password);
	User getByUsername(String username);
}
