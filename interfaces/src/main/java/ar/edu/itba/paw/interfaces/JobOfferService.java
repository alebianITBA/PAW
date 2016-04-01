package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.JobOffer;

public interface JobOfferService {

	public void create(String title, String description, Long userId);

	public void delete(Long id);

	public void update(Long id, String title, String description);

	public Long count();

	public JobOffer find(Long id);

	public List<JobOffer> all();

	public List<JobOffer> userJobOffers(Long userId);

}
