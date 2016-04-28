package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.JobOfferSkillService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferDao jobOfferDao;
	
	@Autowired
	private JobOfferSkillService jobOfferSkillService;

	public void setJobOfferDao(JobOfferDao jobOfferDao) {
		this.jobOfferDao = jobOfferDao;
	}

	public Long create(String title, String description, Long userId) {
		return jobOfferDao.create(title, description, userId);
	}

	public void delete(Long id) {
		jobOfferDao.delete(id);
	}

	public void update(Long id, String title, String description) {
		jobOfferDao.update(id, title, description);
	}

	public Long count() {
		return jobOfferDao.count();
	}

	public JobOffer find(Long id) {
		JobOffer offer = jobOfferDao.find(id);
		addSkillsToOffer(offer);
		return offer;
	}

	public List<JobOffer> all() {
		List<JobOffer> offers = jobOfferDao.all();
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
		}
		return offers;
	}

	public List<JobOffer> userJobOffers(Long userId) {
		List<JobOffer> offers = jobOfferDao.userJobOffers(userId);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
		}
		return offers;
	}
	
	public List<JobOffer> withSkills(List<Skill> userSkills) {
		List<JobOffer> offers = jobOfferDao.withSkills(userSkills);
		for (JobOffer offer : offers) {
			addSkillsToOffer(offer);
		}
		return offers;
	}
	
	private void addSkillsToOffer(JobOffer offer) {
		List<Skill> skills = jobOfferSkillService.jobOfferSkills(offer.getId());
		offer.setSkills(skills);
	}

}
