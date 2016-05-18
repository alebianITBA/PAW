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
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Override
	@Transactional
	public void create(String description, User user, JobOffer jobOffer) {
		jobApplicationDao.create(description, user, jobOffer);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		jobApplicationDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Long id, String description) {
		jobApplicationDao.update(id, description);
	}

	@Override
	@Transactional
	public Long count() {
		return jobApplicationDao.count();
	}

	@Override
	@Transactional
	public JobApplication find(Long id) {
		return jobApplicationDao.find(id);
	}

	@Override
	@Transactional
	public List<JobApplication> all() {
		return jobApplicationDao.all();
	}

	@Override
	@Transactional
	public List<JobApplication> all(Integer page, Integer perPage) {
		return jobApplicationDao.all(page, perPage);
	}

	@Override
	@Transactional
	public List<JobApplication> userJobApplications(Long userId) {
		return jobApplicationDao.userJobApplications(userId);
	}

	@Override
	@Transactional
	public List<JobApplication> userJobApplications(Long userId, Integer page, Integer perPage) {
		return jobApplicationDao.userJobApplications(userId, page, perPage);
	}

	@Override
	@Transactional
	public List<JobApplication> jobOfferApplications(Long jobOfferId) {
		return jobApplicationDao.jobOfferApplications(jobOfferId);
	}

	@Override
	@Transactional
	public List<JobApplication> jobOfferApplications(Long jobOfferId, Integer page, Integer perPage) {
		return jobApplicationDao.jobOfferApplications(jobOfferId, page, perPage);
	}

	@Override
	@Transactional
	public void removeJobOfferApplications(Long jobOfferId) {
		jobApplicationDao.removeJobOfferApplications(jobOfferId);
	}

}
