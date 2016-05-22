package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Skill;

import java.util.List;

public interface SkillDao {

  Skill create(String name);

  void delete(Long id);

  Skill update(Long id, String name);

  Long count();

  Skill find(Long id);

  Skill findByName(String name);

  List<Skill> all();

}
