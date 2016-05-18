package ar.edu.itba.paw.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

@Controller
@SessionAttributes
public class JobOffersController extends ApplicationController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private JobApplicationService jobApplicationService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private JobOfferSkillService jobOfferSkillService;

	@RequestMapping(path = "/job_offers", method = RequestMethod.GET)
	public ModelAndView jobOffers(@RequestParam(required = false, value = "skill_id") final Long skillId,
			@ModelAttribute("jobOfferForm") JobOfferForm jobOfferForm, final BindingResult binding) {
		ModelAndView mav = new ModelAndView("job_offers/index");
		mav.addAllObjects(getJobOffersMap(skillId));
		mav.addObject("jobOfferForm", jobOfferForm);
		// TODO: This doesn't work
		mav.addObject("errors", binding);
		return mav;
	}

	@RequestMapping(path = "/job_offers", method = RequestMethod.POST)
	public String createJobOffer(@Valid @ModelAttribute("jobOfferForm") JobOfferForm jobOfferForm,
			final BindingResult binding, RedirectAttributes attr) {
		if (!binding.hasErrors()) {
			jobOfferService.create(jobOfferForm.getTitle(), jobOfferForm.getDescription(), getLoggedUser().getId(),
					jobOfferForm.getSelectedSkillIds());
		}
		attr.addFlashAttribute("org.springframework.validation.BindingResult.jobOfferForm", binding);
		attr.addFlashAttribute("jobOfferForm", jobOfferForm);
		return "redirect:/job_offers";
	}

	@RequestMapping(path = "/job_offers/{id}", method = RequestMethod.GET)
	public ModelAndView getJobOffer(@PathVariable final Long id) {
		final ModelAndView mav = new ModelAndView("job_offers/show");
		mav.addObject("job", jobOfferService.find(id));

		mav.addObject("userApply", new User());

		java.util.List<JobApplication> applications = jobApplicationService.jobOfferApplications(id);
		java.util.List<Skill> jobOfferSkills = jobOfferSkillService.jobOfferSkills(id);

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

	@RequestMapping(path = "/job_offers/{id}", method = RequestMethod.DELETE)
	public String deleteJobOffer(@PathVariable final Long id) {
		JobOffer offer = jobOfferService.find(id);
		if (offer.getUserId() == getLoggedUser().getId()) {
			jobOfferService.delete(id);
		}
		return "redirect:/users/me";
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
		return map;
	}

}
