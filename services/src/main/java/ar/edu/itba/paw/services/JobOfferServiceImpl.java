package ar.edu.itba.paw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@Service
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferDao jobOfferDao;

	@Autowired
	private SkillService skillService;

	public void setJobOfferDao(JobOfferDao jobOfferDao) {
		this.jobOfferDao = jobOfferDao;
	}

	@Override
	public JobOffer create(String title, String description, User user) {
		return jobOfferDao.create(title, description, user);
	}

	@Override
	public JobOffer create(String title, String description, User user, String skills) {
		List<Skill> skillList = new ArrayList<Skill>();
		if (skills != null && !skills.isEmpty()) {
			String[] skillIds = skills.split(",");
			for (String skillId : skillIds) {
				skillList.add(skillService.find(Long.getLong(skillId)));
			}
		}
		return jobOfferDao.create(title, description, user, skillList);
	}

	@Override
	public void delete(Long id) {
		jobOfferDao.delete(id);
	}

	@Override
	public JobOffer update(Long id, String title, String description) {
		return jobOfferDao.update(id, title, description);
	}

	@Override
	public Long count() {
		return jobOfferDao.count();
	}

	@Override
	public JobOffer find(Long id) {
		return jobOfferDao.find(id);
	}

	@Override
	public List<JobOffer> all() {
		return jobOfferDao.all();
	}

	@Override
	public List<JobOffer> all(Integer page, Integer perPage) {
		return jobOfferDao.all(page, perPage);
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId) {
		return jobOfferDao.userJobOffers(userId);
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage) {
		return jobOfferDao.userJobOffers(userId, page, perPage);
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills) {
		if (skills == null || skills.isEmpty()) {
			return all();
		}
		return jobOfferDao.withSkills(skills);
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage) {
		if (skills == null || skills.isEmpty()) {
			return all(page, perPage);
		}
		return jobOfferDao.withSkills(skills, page, perPage);
	}

	@Override
	public List<JobOffer> notFromUser(Long userId) {
		return jobOfferDao.notFromUser(userId);
	}

	@Override
	public List<JobOffer> notFromUser(Long userId, Integer page, Integer perPage) {
		return jobOfferDao.notFromUser(userId, page, perPage);
	}

}
