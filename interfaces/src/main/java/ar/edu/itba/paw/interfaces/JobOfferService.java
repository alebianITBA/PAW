package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.JobOffer;

public interface JobOfferService {

	void create(String title, String description, Long userId);

	void delete(Long id);

	void update(Long id, String title, String description);

	Long count();

	JobOffer find(Long id);

	List<JobOffer> all();

	List<JobOffer> userJobOffers(Long userId);

}
