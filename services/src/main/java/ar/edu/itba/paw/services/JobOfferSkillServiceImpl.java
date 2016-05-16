package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.JobOfferSkillDao;
import ar.edu.itba.paw.interfaces.JobOfferSkillService;
import ar.edu.itba.paw.models.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferSkillServiceImpl implements JobOfferSkillService {

  @Autowired
  private JobOfferSkillDao jobOfferSkillDao;

  public void setPostDao(JobOfferSkillDao jobOfferSkillDao) {
    this.jobOfferSkillDao = jobOfferSkillDao;
  }

  @Override
  public void create(Long jobOfferId, Long skillId) {
    jobOfferSkillDao.create(jobOfferId, skillId);
  }

  @Override
  public void delete(Long id) {
    jobOfferSkillDao.delete(id);
  }

  @Override
  public void removeJobOfferSkills(Long jobOfferId) {
    jobOfferSkillDao.removeJobOfferSkills(jobOfferId);
  }

  @Override
  public void removeJobOfferSkill(Long jobOfferId, Long skillId) {
    jobOfferSkillDao.removeJobOfferSkill(jobOfferId, skillId);
  }

  @Override
  public List<Skill> jobOfferSkills(Long jobOfferId) {
    return jobOfferSkillDao.jobOfferSkills(jobOfferId);
  }

}
