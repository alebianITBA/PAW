package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.User;

public interface UserService {

	public void create(String email, String password);

	public void delete(Long id);

	public void update(Long id, String firstName, String lastName, String email, String password);

	public User findByEmail(String email);

	public Long count();

	public User find(Long id);

	public List<User> all();

}
