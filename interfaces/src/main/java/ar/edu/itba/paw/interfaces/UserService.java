package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.User;

public interface UserService {

	void create(String firstName, String lastName, String email, String password);

	void delete(Long id);

	void update(Long id, String firstName, String lastName, String email, String password);

	User findByEmail(String email);

	Long count();

	User find(Long id);

	List<User> all();
	
	List<User> all(Integer page, Integer perPage);

}
