package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	SkillDao skillDao;

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	public Skill create(String name) {
		return skillDao.create(name);
	}

	@Override
	public void delete(Long id) {
		skillDao.delete(id);
	}

	@Override
	public Skill update(Long id, String name) {
		return skillDao.update(id, name);
	}

	@Override
	public Long count() {
		return skillDao.count();
	}

	@Override
	public Skill find(Long id) {
		return skillDao.find(id);
	}

	@Override
	public Skill findByName(String name) {
		return skillDao.findByName(name);
	}

	@Override
	public List<Skill> all() {
		return skillDao.all();
	}

}
