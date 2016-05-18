package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.interfaces.UserSkillService;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private UserSkillService userSkillService;

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void create(String firstName, String lastName, String email, String password) {
    userDao.create(firstName, lastName, email, password);
  }

  @Override
  public void delete(Long id) {
    userDao.delete(id);
  }

  @Override
  public void update(Long id, String firstName, String lastName, String email, String password) {
    userDao.update(id, firstName, lastName, email, password);
  }

  @Override
  public User findByEmail(String email) {
    User user = userDao.findByEmail(email);
    if(user != null) {
    	addSkillsToUser(user);
    }
    return user;
  }

  @Override
  public Long count() {
    return userDao.count();
  }

  @Override
  public User find(Long id) {
    User user = userDao.find(id);
    addSkillsToUser(user);
    return user;
  }

  @Override
  public List<User> all() {
    List<User> users = userDao.all();
    for (User user : users) {
      addSkillsToUser(user);
    }
    return users;
  }

  @Override
  public List<User> all(Integer page, Integer perPage) {
    List<User> users = userDao.all(page, perPage);
    for (User user : users) {
      addSkillsToUser(user);
    }
    return users;
  }

  private void addSkillsToUser(User user) {
    List<Skill> skills = userSkillService.userSkills(user.getId());
    user.setSkills(skills);
  }

}
