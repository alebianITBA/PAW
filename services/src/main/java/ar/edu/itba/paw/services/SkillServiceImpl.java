package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	SkillDao skillDao;

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	@Transactional
	public void create(String name) {
		skillDao.create(name);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		skillDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Long id, String name) {
		skillDao.update(id, name);
	}

	@Override
	@Transactional
	public Long count() {
		return skillDao.count();
	}

	@Override
	@Transactional
	public Skill find(Long id) {
		return skillDao.find(id);
	}

	@Override
	@Transactional
	public Skill findByName(String name) {
		return skillDao.findByName(name);
	}

	@Override
	@Transactional
	public List<Skill> all() {
		return skillDao.all();
	}

}
