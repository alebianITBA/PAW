package ar.edu.itba.paw.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.forms.JobOfferForm;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.JobOfferSkillService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@Controller
@SessionAttributes
@RequestMapping("/job_offers")
public class JobOffersController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JobOfferSkillService jobOfferSkillService;
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public ModelAndView jobOffers() {
		final ModelAndView mav = new ModelAndView("job_offers/index");
		mav.addObject("job_offers", jobOfferService.all());
		return mav;
	}

	@RequestMapping(path = "/{jobOfferId}", method = RequestMethod.GET)
	public ModelAndView getJobOffer(@PathVariable final Long jobOfferId) {
		final ModelAndView mav = new ModelAndView("job_offers/show");
		mav.addObject("job", jobOfferService.find(jobOfferId));
		mav.addObject("userApply", new User());
		java.util.List<JobApplication> applications = jobApplicationService.jobOfferApplications(jobOfferId);
		java.util.List<Skill> jobOfferSkills = jobOfferSkillService.jobOfferSkills(jobOfferId);
		mav.addObject("jobOfferSkills", jobOfferSkills);
		mav.addObject("quantityApplications", applications != null ? applications.size() : 0);
		return mav;
	}
	
	@RequestMapping(path = "/{id}/apply", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView applyJobOfferByEmail(@PathVariable final Long id,
			@ModelAttribute("userApply") User userApply,
            Map<String, Object> model) {
		User user = userService.findByEmail(userApply.getEmail());
		if (user != null) {
			jobApplicationService.create("Email application", user.getId(), id);	
		} else {
			
			// TODO: MOSTRAR ERROR DE USUARIO NO ENCONTRADO
			
		}
		
		return getJobOffer(id);
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public ModelAndView showJobOfferForm(@ModelAttribute("jobOfferForm") JobOfferForm jobOfferForm,
			Map<String, Object> model) {
		final ModelAndView mav = new ModelAndView("job_offers/create");
		java.util.ArrayList<Skill> skills = (java.util.ArrayList<Skill>) skillService.all();
		model.put("skills", skills);
		return mav;
	}
	
	@RequestMapping(path = "/post", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public ModelAndView addJobOffer(@Valid @ModelAttribute("jobOfferForm") JobOfferForm jobOfferForm,
			final BindingResult errors) 
	{
		if (errors.hasErrors()) {
            return showJobOfferForm(jobOfferForm, new HashMap<String, Object>());
        } else {
        	Long jobOfferId = jobOfferService.create(jobOfferForm.getTitle(), jobOfferForm.getDescription(), 2L);
        	if (jobOfferForm.getSelectedSkillIds() != null && !jobOfferForm.getSelectedSkillIds().isEmpty()) {
        		String[] skillIds = jobOfferForm.getSelectedSkillIds().split(",");
        		for(String skillId : skillIds) {
        			jobOfferSkillService.create(jobOfferId, new Long(skillId));
        		}
        	}
        	return jobOffers();
        }
    }
}
