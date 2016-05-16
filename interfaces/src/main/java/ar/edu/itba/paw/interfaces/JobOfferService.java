package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

import java.util.List;

public interface JobOfferService {

  Long create(String title, String description, Long userId);

  void delete(Long id);

  void update(Long id, String title, String description);

  Long count();

  JobOffer find(Long id);

  List<JobOffer> all();

  List<JobOffer> all(Integer page, Integer perPage);

  List<JobOffer> userJobOffers(Long userId);

  List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage);

  List<JobOffer> withSkills(List<Skill> skills);

  List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage);

}
