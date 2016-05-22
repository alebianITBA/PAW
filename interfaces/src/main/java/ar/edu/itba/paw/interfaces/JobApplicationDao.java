package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;

public interface JobApplicationDao {

	JobApplication create(String description, User user, JobOffer jobOffer);

	void delete(Long id);

	JobApplication update(Long id, String description);

	Long count();

	JobApplication find(Long id);

	List<JobApplication> all();

	List<JobApplication> all(Integer page, Integer perPage);

	List<JobApplication> userJobApplications(Long userId);

	List<JobApplication> userJobApplications(Long userId, Integer page, Integer perPage);

	List<JobApplication> jobOfferApplications(Long jobOfferId);

	List<JobApplication> jobOfferApplications(Long jobOfferId, Integer page, Integer perPage);

	void removeJobOfferApplications(Long jobOfferId);

}
