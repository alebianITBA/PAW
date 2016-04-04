package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.Skill;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	SkillDao skillDao;

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	public void create(String name) {
		skillDao.create(name);
	}

	public void delete(Long id) {
		skillDao.delete(id);
	}

	public void update(Long id, String name) {
		skillDao.update(id, name);
	}

	public Long count() {
		return skillDao.count();
	}

	public Skill find(Long id) {
		return skillDao.find(id);
	}

	public Skill findByName(String name) {
		return skillDao.findByName(name);
	}

	public List<Skill> all() {
		return skillDao.all();
	}

}
