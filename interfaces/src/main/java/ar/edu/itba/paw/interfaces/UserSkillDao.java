package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Skill;

public interface UserSkillDao {

	void create(Long userId, Long skillId);

	void delete(Long id);

	void removeUserSkill(Long userId, Long skillId);

	List<Skill> userSkills(Long userId);

}
