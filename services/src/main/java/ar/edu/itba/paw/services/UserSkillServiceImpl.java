package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.UserSkillDao;
import ar.edu.itba.paw.interfaces.UserSkillService;
import ar.edu.itba.paw.models.Skill;

@Service
public class UserSkillServiceImpl implements UserSkillService {

	@Autowired
	private UserSkillDao userSkillDao;

	public void setPostDao(UserSkillDao userSkillDao) {
		this.userSkillDao = userSkillDao;
	}

	public void create(Long userId, Long skillId) {
		userSkillDao.create(userId, skillId);
	}

	public void delete(Long id) {
		userSkillDao.delete(id);
	}

	public void removeUserSkill(Long userId, Long skillId) {
		userSkillDao.removeUserSkill(userId, skillId);
	}

	public void removeUserSkill(Long userId, String skillName) {
		userSkillDao.removeUserSkill(userId, skillName);
	}

	public List<Skill> userSkills(Long userId) {
		return userSkillDao.userSkills(userId);
	}

}
