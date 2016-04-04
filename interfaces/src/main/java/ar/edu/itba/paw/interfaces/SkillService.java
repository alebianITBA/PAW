package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Skill;

public interface SkillService {

	void create(String name);

	void delete(Long id);

	void update(Long id, String name);

	Long count();

	Skill find(Long id);

	Skill findByName(String name);

	List<Skill> all();

}
