package ar.edu.itba.paw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.User;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private UserService userService;

	@Override
	public void create(String description, Long userId, Long jobOfferId) {
		jobApplicationDao.create(description, userId, jobOfferId);
	}

	@Override
	public void delete(Long id) {
		jobApplicationDao.delete(id);
	}

	@Override
	public void update(Long id, String description) {
		jobApplicationDao.update(id, description);
	}

	@Override
	public Long count() {
		return jobApplicationDao.count();
	}

	@Override
	public JobApplication find(Long id) {
		JobApplication application = jobApplicationDao.find(id);
		addUserToApplication(application);
		return application;
	}

	@Override
	public List<JobApplication> all() {
		List<JobApplication> applications = jobApplicationDao.all();
		for (JobApplication application : applications) {
			addUserToApplication(application);
		}
		return applications;
	}

	@Override
	public List<JobApplication> userJobApplications(Long userId) {
		List<JobApplication> applications = jobApplicationDao.userJobApplications(userId);
		for (JobApplication application : applications) {
			addUserToApplication(application);
		}
		return applications;
	}

	@Override
	public List<JobApplication> jobOfferApplications(Long jobOfferId) {
		List<JobApplication> applications = jobApplicationDao.jobOfferApplications(jobOfferId);
		for (JobApplication application : applications) {
			addUserToApplication(application);
		}
		return applications;
	}

	private void addUserToApplication(JobApplication application) {
		User user = userService.find(application.getUserId());
		application.setUser(user);
	}

}
