package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.dto.JobOfferDTO;
import ar.edu.itba.paw.enums.JobOfferStatus;
import ar.edu.itba.paw.forms.JobOfferForm;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.JobOfferSkillService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@Controller
@SessionAttributes
@RequestMapping("/job_offers")
public class JobOffersController extends ApplicationController {

  @Autowired
  private JobOfferService jobOfferService;

  @Autowired
  private JobApplicationService jobApplicationService;

  @Autowired
  private SkillService skillService;

  @Autowired
  private JobOfferSkillService jobOfferSkillService;

  @RequestMapping(path = "", method = RequestMethod.GET)
  public ModelAndView jobOffers(@RequestParam(required = false, value = "skill_id") final Long skillId,
      @ModelAttribute("jobOfferForm") JobOfferForm jobOfferForm, final BindingResult errors) {
    return getJobOffersView(skillId);
  }

  @RequestMapping(path = "/{jobOfferId}", method = RequestMethod.GET)
  public ModelAndView getJobOffer(@PathVariable final Long jobOfferId) {
    final ModelAndView mav = new ModelAndView("job_offers/show");
    mav.addObject("loggedUser", getLoggedUser());
    mav.addObject("job", jobOfferService.find(jobOfferId));

    mav.addObject("userApply", new User());

    java.util.List<JobApplication> applications = jobApplicationService.jobOfferApplications(jobOfferId);
    java.util.List<Skill> jobOfferSkills = jobOfferSkillService.jobOfferSkills(jobOfferId);

    boolean alreadyApplied = false;
    for (JobApplication application : applications) {
      if (application.getUserId() == getLoggedUser().getId()) {
        alreadyApplied = true;
        break;
      }
    }

    mav.addObject("jobOfferSkills", jobOfferSkills);
    mav.addObject("quantityApplications", applications != null ? applications.size() : 0);
    mav.addObject("applications", applications);
    mav.addObject("alreadyApplied", alreadyApplied);
    return mav;
  }

  @RequestMapping(path = "/{jobOfferId}", method = RequestMethod.DELETE)
  public ModelAndView deleteJobOffer(@PathVariable final Long jobOfferId) {
    JobOffer offer = jobOfferService.find(jobOfferId);
    if(offer.getUserId() == getLoggedUser().getId()) {
      jobOfferService.delete(jobOfferId);
    }
    return new ModelAndView("redirect:/users/me");
  }

  @RequestMapping(path = "/{id}/apply", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public ModelAndView applyJobOffer(@PathVariable final Long id) {
    User loggedUser = getLoggedUser();
    jobApplicationService.create("Basic application", loggedUser.getId(), id);
    return getJobOffersView(null);
  }

  @RequestMapping(path = "/create_offer", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public ModelAndView createJobOffer(@Valid @ModelAttribute("jobOfferForm") JobOfferForm jobOfferForm,
      final BindingResult errors) {
    if (errors.hasErrors()) {
      return jobOffers(null, jobOfferForm, errors);
    } else {
      Long jobOfferId = jobOfferService.create(jobOfferForm.getTitle(), jobOfferForm.getDescription(),
          getLoggedUser().getId());
      if (jobOfferForm.getSelectedSkillIds() != null && !jobOfferForm.getSelectedSkillIds().isEmpty()) {
        String[] skillIds = jobOfferForm.getSelectedSkillIds().split(",");
        for (String skillId : skillIds) {
          jobOfferSkillService.create(jobOfferId, new Long(skillId));
        }
      }
      return getJobOffersView(null);
    }
  }

  private Map<String, Object> getJobOffersMap(Long skillId) {
    Map<String, Object> map = new HashMap<String, Object>();

    User loggedUser = getLoggedUser();

    List<JobOfferDTO> jobOfferListDTO = new ArrayList<JobOfferDTO>();
    List<JobOffer> jobOfferList = new ArrayList<JobOffer>();
    List<JobApplication> alreadyApplies = jobApplicationService.userJobApplications(loggedUser.getId());

    if (skillId != null) {
      List<Skill> skills = new LinkedList<Skill>();
      skills.add(new Skill(skillId, "", null));
      jobOfferList = jobOfferService.withSkills(skills, 1, 50);
    } else {
      jobOfferList = jobOfferService.all(1, 50);
    }

    // Deberia ir a un helper
    for (JobOffer offer : jobOfferList) {
      JobOfferDTO offerDTO = JobOfferDTO.fromModel(offer);
      if (offerDTO.getUserId() == loggedUser.getId()) {
        offerDTO.setStatus(JobOfferStatus.OFFER_OWNER);
      } else {
        boolean alreadyApply = false;
        for (JobApplication application : alreadyApplies) {
          if (application.getJobOfferId() == offerDTO.getId()) {
            alreadyApply = true;
            break;
          }
        }

        if (alreadyApply) {
          offerDTO.setStatus(JobOfferStatus.ALREADY_APPLIED);
        } else {
          offerDTO.setStatus(JobOfferStatus.READY_TO_APPLY);
        }
      }
      jobOfferListDTO.add(offerDTO);
    }

    map.put("jobOfferForm", new JobOfferForm());
    map.put("job_offers", jobOfferListDTO);
    map.put("skills", skillService.all());
    map.put("loggedUser", loggedUser);
    return map;
  }

  private ModelAndView getJobOffersView(Long skillId) {
    final ModelAndView mav = new ModelAndView("job_offers/index");
    mav.addAllObjects(getJobOffersMap(skillId));
    return mav;
  }

}
