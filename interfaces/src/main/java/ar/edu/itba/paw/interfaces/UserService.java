package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.util.List;

public interface UserService {

  User create(String firstName, String lastName, String email, String password);

  void delete(Long id);

  User update(Long id, String firstName, String lastName, String email, String password);

  User findByEmail(String email);

  Long count();

  User find(Long id);

  List<User> all();

  List<User> all(Integer page, Integer perPage);

}
