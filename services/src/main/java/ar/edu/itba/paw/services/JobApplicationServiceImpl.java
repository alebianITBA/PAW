package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Override
	public JobApplication create(String description, User user, JobOffer jobOffer) {
		return jobApplicationDao.create(description, user, jobOffer);
	}

	@Override
	public void delete(Long id) {
		jobApplicationDao.delete(id);
	}

	@Override
	public JobApplication update(Long id, String description) {
		return jobApplicationDao.update(id, description);
	}

	@Override
	public Long count() {
		return jobApplicationDao.count();
	}

	@Override
	public JobApplication find(Long id) {
		return jobApplicationDao.find(id);
	}

	@Override
	public List<JobApplication> all() {
		return jobApplicationDao.all();
	}

	@Override
	public List<JobApplication> all(Integer page, Integer perPage) {
		return jobApplicationDao.all(page, perPage);
	}

	@Override
	public List<JobApplication> userJobApplications(Long userId) {
		return jobApplicationDao.userJobApplications(userId);
	}

	@Override
	public List<JobApplication> userJobApplications(Long userId, Integer page, Integer perPage) {
		return jobApplicationDao.userJobApplications(userId, page, perPage);
	}

	@Override
	public List<JobApplication> jobOfferApplications(Long jobOfferId) {
		return jobApplicationDao.jobOfferApplications(jobOfferId);
	}

	@Override
	public List<JobApplication> jobOfferApplications(Long jobOfferId, Integer page, Integer perPage) {
		return jobApplicationDao.jobOfferApplications(jobOfferId, page, perPage);
	}

	@Override
	public void removeJobOfferApplications(Long jobOfferId) {
		jobApplicationDao.removeJobOfferApplications(jobOfferId);
	}

}
