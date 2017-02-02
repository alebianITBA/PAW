package ar.edu.itba.paw.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@Service
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

  @Autowired
  private JobOfferDao jobOfferDao;

  @Autowired
  private SkillService skillService;

  @Autowired
  private JobApplicationService jobApplicationService;

  public void setJobOfferDao(JobOfferDao jobOfferDao) {
    this.jobOfferDao = jobOfferDao;
  }

  @Override
  public JobOffer create(String title, String description, User user) {
    return jobOfferDao.create(title, description, user);
  }

  @Override
  public JobOffer create(String title, String description, User user, String skills) {
    List<Skill> skillList = skillListFromString(skills);
    return jobOfferDao.create(title, description, user, skillList);
  }

  @Override
  public void delete(Long id) {
    jobOfferDao.delete(id);
  }

  @Override
  public JobOffer update(Long id, String title, String description) {
    return jobOfferDao.update(id, title, description);
  }

  @Override
  public JobOffer update(Long id, String title, String description, String skills) {
    List<Skill> skillList = skillListFromString(skills);
    return jobOfferDao.update(id, title, description, skillList);
  }

  @Override
  public JobOffer update(Long id, Date closedAt) {
    return jobOfferDao.update(id, closedAt);
  }

  @Override
  public Long count() {
    return jobOfferDao.count();
  }

  @Override
  public JobOffer find(Long id) {
    return jobOfferDao.find(id);
  }

  @Override
  public List<JobOffer> all() {
    return jobOfferDao.all();
  }

  @Override
  public List<JobOffer> all(Integer page, Integer perPage) {
    return jobOfferDao.all(page, perPage);
  }

  @Override
  public List<JobOffer> userJobOffers(Long userId) {
    return jobOfferDao.userJobOffers(userId);
  }

  @Override
  public List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage) {
    return jobOfferDao.userJobOffers(userId, page, perPage);
  }

  @Override
  public List<JobOffer> withSkills(List<Skill> skills) {
    if (skills == null || skills.isEmpty()) {
      return all();
    }
    return jobOfferDao.withSkills(skills);
  }

  @Override
  public List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage) {
    if (skills == null || skills.isEmpty()) {
      return all(page, perPage);
    }
    return jobOfferDao.withSkills(skills, page, perPage);
  }

  @Override
  public Long withSkillsCount(List<Skill> skills) {
    return jobOfferDao.withSkillsCount(skills);
  }

  @Override
  public List<JobOffer> notFromUser(Long userId) {
    return jobOfferDao.notFromUser(userId);
  }

  @Override
  public List<JobOffer> notFromUser(Long userId, Integer page, Integer perPage) {
    return jobOfferDao.notFromUser(userId, page, perPage);
  }

  @Override
  public List<JobOffer> notApplied(Long userId) {
    List<JobOffer> result = null;
    List<JobApplication> applications = jobApplicationService.userJobApplications(userId);
    if (applications != null && !applications.isEmpty()) {
      result = jobOfferDao.notApplied(userId, applications);
      if (result == null || result.isEmpty()) {
        result = notFromUser(userId);
      }
    } else {
      result = notFromUser(userId);
    }
    return result;
  }

  @Override
  public List<JobOffer> notApplied(Long userId, Integer page, Integer perPage) {
    List<JobOffer> result = null;
    List<JobApplication> applications = jobApplicationService.userJobApplications(userId);
    if (applications != null && !applications.isEmpty()) {
      result = jobOfferDao.notApplied(userId, applications, page, perPage);
      if (result == null || result.isEmpty()) {
        result = notFromUser(userId, page, perPage);
      }
    } else {
      result = notFromUser(userId, page, perPage);
    }
    return result;
  }

  @Override
  public List<JobOffer> notAppliedWithSkills(Long userId, List<Skill> skills) {
    List<JobOffer> result = null;

    List<JobApplication> applications = jobApplicationService.userJobApplications(userId);

    if (applications != null && !applications.isEmpty()) {
      if (skills != null && !skills.isEmpty()) {
        result = jobOfferDao.notAppliedWithSkills(userId, applications, skills);
        if (result == null || result.isEmpty()) {
          result = notApplied(userId);
        }
      } else {
        result = notApplied(userId);
      }
    } else {
      result = notFromUser(userId);
    }

    return result;
  }

  @Override
  public List<JobOffer> notAppliedWithSkills(Long userId, List<Skill> skills, Integer page, Integer perPage) {
List<JobOffer> result = null;

    List<JobApplication> applications = jobApplicationService.userJobApplications(userId);

    if (applications != null && !applications.isEmpty()) {
      if (skills != null && !skills.isEmpty()) {
        result = jobOfferDao.notAppliedWithSkills(userId, applications, skills, page, perPage);
        if (result == null || result.isEmpty()) {
          result = notApplied(userId, page, perPage);
        }
      } else {
        result = notApplied(userId, page, perPage);
      }
    } else {
      result = notFromUser(userId, page, perPage);
    }

    return result;
  }

  @Override
  public List<JobOffer> recommendedFor(User user, int page, int perPage) {
    List<JobOffer> offers;
    if (user.getSkills().isEmpty()) {
      offers = notApplied(user.getId(), page, perPage);
    } else {
      // We give the user personalized offers
      offers = notAppliedWithSkills(user.getId(), user.getSkills(), page, perPage);
    }
    // Finally, if we couldn't find any offers for that user
    if (offers.isEmpty()) {
      offers = notFromUser(user.getId(), page, perPage);
    }
    return offers;
  }

  private List<Skill> skillListFromString(String skills) {
    List<Skill> skillList = new ArrayList<Skill>();
    if (skills != null && !skills.isEmpty()) {
      String[] skillIds = skills.split(",");
      for (String skillId : skillIds) {
        skillList.add(skillService.find(Long.parseLong(skillId)));
      }
    }
    return skillList;
  }

}
