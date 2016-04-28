package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferDao jobOfferDao;

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
		return jobOfferDao.find(id);
	}

	public List<JobOffer> all() {
		return jobOfferDao.all();
	}

	public List<JobOffer> userJobOffers(Long userId) {
		return jobOfferDao.userJobOffers(userId);
	}
	
	public List<JobOffer> withSkills(List<Skill> userSkills) {
		return jobOfferDao.withSkills(userSkills);
	}

}
