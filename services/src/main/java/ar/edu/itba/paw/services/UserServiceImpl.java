package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private SkillService skillService;

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
  public User update(Long id, String firstName, String lastName, String skills) {
    List<Skill> skillList = skillListFromString(skills);
    return userDao.update(id, firstName, lastName, skillList);
  }

  @Override
  public User updateSkills(Long id, List<Skill> skills) {
    return userDao.updateSkills(id, skills);
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

  private List<Skill> skillListFromString(String skills) {
    List<Skill> skillList = new ArrayList<Skill>();
    if (skills != null && !skills.isEmpty()) {
      String[] skillIds = skills.split(",");
      for (String skillId : skillIds) {
        skillList.add(skillService.find(Long.parseLong(skillId)));
      }
    }
    return skillList;
  }
}
