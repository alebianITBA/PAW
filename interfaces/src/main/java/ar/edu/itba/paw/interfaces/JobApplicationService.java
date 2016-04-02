package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.JobApplication;

public interface JobApplicationService {

	void create(String description, Long userId, Long jobOfferId);

	void delete(Long id);

	void update(Long id, String description);

	Long count();

	JobApplication find(Long id);

	List<JobApplication> all();

	List<JobApplication> userJobApplications(Long userId);

	List<JobApplication> jobOfferApplications(Long jobOfferId);

}