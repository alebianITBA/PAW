package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Skill;

import java.util.List;

public interface JobOfferSkillDao {

  void create(Long jobOfferId, Long skillId);

  void delete(Long id);

  void removeJobOfferSkills(Long jobOfferId);

  void removeJobOfferSkill(Long jobOfferId, Long skillId);

  List<Skill> jobOfferSkills(Long jobOfferId);

}
