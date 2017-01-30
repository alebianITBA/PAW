package ar.edu.itba.paw.interfaces;

import java.util.Date;
import java.util.List;

import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

public interface JobOfferDao {

  JobOffer create(String title, String description, User user);

  JobOffer create(String title, String description, User user, List<Skill> skills);

  void delete(Long id);

  JobOffer update(Long id, String title, String description);
  
  JobOffer update(Long id, String title, String description, List<Skill> skills);
  
  JobOffer update(Long id, Date closedAt);

  Long count();

  JobOffer find(Long id);

  List<JobOffer> all();

  List<JobOffer> all(Integer page, Integer perPage);

  List<JobOffer> userJobOffers(Long userId);

  List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage);

  List<JobOffer> withSkills(List<Skill> skills);

  List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage);
  
  Long withSkillsCount(List<Skill> skills);

  List<JobOffer> notFromUser(Long userId);

  List<JobOffer> notFromUser(Long userId, Integer page, Integer perPage);
  
  List<JobOffer> notApplied(Long userId, List<JobApplication> applications);
  
  List<JobOffer> notApplied(Long userId, List<JobApplication> applications, Integer page, Integer perPage);
  
  List<JobOffer> notAppliedWithSkills(Long userId, List<JobApplication> applications, List<Skill> skills);
  
  List<JobOffer> notAppliedWithSkills(Long userId, List<JobApplication> applications, List<Skill> skills, Integer page, Integer perPage);

}
