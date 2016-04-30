package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Skill;

public interface JobOfferSkillDao {

	void create(Long jobOfferId, Long skillId);

	void delete(Long id);

	void removeJobOfferSkill(Long jobOfferId, Long skillId);

	List<Skill> jobOfferSkills(Long jobOfferId);

}