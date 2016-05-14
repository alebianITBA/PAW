package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.JobOfferSkillService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferDao jobOfferDao;
	
	@Autowired
	private JobApplicationService jobApplicationService;

	@Autowired
	private JobOfferSkillService jobOfferSkillService;

	@Autowired
	private UserService userService;

	public void setJobOfferDao(JobOfferDao jobOfferDao) {
		this.jobOfferDao = jobOfferDao;
	}

	@Override
	public Long create(String title, String description, Long userId) {
		return jobOfferDao.create(title, description, userId);
	}

	@Override
	public void delete(Long id) {
		jobOfferSkillService.removeJobOfferSkills(id);
		jobApplicationService.removeJobOfferApplications(id);
		jobOfferDao.delete(id);
	}

	@Override
	public void update(Long id, String title, String description) {
		jobOfferDao.update(id, title, description);
	}

	@Override
	public Long count() {
		return jobOfferDao.count();
	}

	@Override
	public JobOffer find(Long id) {
		JobOffer offer = jobOfferDao.find(id);
		addSkillsToOffer(offer);
		addUserToOffer(offer);
		return offer;
	}

	@Override
	public List<JobOffer> all() {
		List<JobOffer> offers = jobOfferDao.all();
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
			addUserToOffer(offer);
		}
		return offers;
	}

	@Override
	public List<JobOffer> all(Integer page, Integer perPage) {
		List<JobOffer> offers = jobOfferDao.all(page, perPage);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
			addUserToOffer(offer);
		}
		return offers;
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId) {
		List<JobOffer> offers = jobOfferDao.userJobOffers(userId);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
			addUserToOffer(offer);
		}
		return offers;
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage) {
		List<JobOffer> offers = jobOfferDao.userJobOffers(userId, page, perPage);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
			addUserToOffer(offer);
		}
		return offers;
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills) {
		if (skills == null || skills.isEmpty()) {
			return all();
		}

		List<JobOffer> offers = jobOfferDao.withSkills(skills);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
			addUserToOffer(offer);
		}
		return offers;
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage) {
		if (skills == null || skills.isEmpty()) {
			return all(page, perPage);
		}

		List<JobOffer> offers = jobOfferDao.withSkills(skills, page, perPage);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
			addUserToOffer(offer);
		}
		return offers;
	}

	private void addSkillsToOffer(JobOffer offer) {
		List<Skill> skills = jobOfferSkillService.jobOfferSkills(offer.getId());
		offer.setSkills(skills);
	}

	private void addUserToOffer(JobOffer offer) {
		offer.setUser(userService.find(offer.getUserId()));
	}

}
