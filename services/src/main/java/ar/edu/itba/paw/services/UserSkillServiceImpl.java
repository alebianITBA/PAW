package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.UserSkillDao;
import ar.edu.itba.paw.interfaces.UserSkillService;
import ar.edu.itba.paw.models.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillServiceImpl implements UserSkillService {

  @Autowired
  private UserSkillDao userSkillDao;

  public void setPostDao(UserSkillDao userSkillDao) {
    this.userSkillDao = userSkillDao;
  }

  @Override
  public void create(Long userId, Long skillId) {
    userSkillDao.create(userId, skillId);
  }

  @Override
  public void delete(Long id) {
    userSkillDao.delete(id);
  }

  @Override
  public void removeUserSkill(Long userId, Long skillId) {
    userSkillDao.removeUserSkill(userId, skillId);
  }

  @Override
  public List<Skill> userSkills(Long userId) {
    return userSkillDao.userSkills(userId);
  }

}
