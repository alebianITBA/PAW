package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.models.JobApplication;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
	
	@Autowired
	private JobApplicationDao jobApplicationDao;

	public void create(String description, Long userId, Long jobOfferId) {
		jobApplicationDao.create(description, userId, jobOfferId);
	}

	public void delete(Long id) {
		jobApplicationDao.delete(id);
	}

	public void update(Long id, String description) {
		jobApplicationDao.update(id, description);
	}

	public Long count() {
		return jobApplicationDao.count();
	}

	public JobApplication find(Long id) {
		return jobApplicationDao.find(id);
	}

	public List<JobApplication> all() {
		return jobApplicationDao.all();
	}

	public List<JobApplication> userJobApplications(Long userId) {
		return jobApplicationDao.userJobApplications(userId);
	}

	public List<JobApplication> jobOfferApplications(Long jobOfferId) {
		return jobApplicationDao.jobOfferApplications(jobOfferId);
	}

}
