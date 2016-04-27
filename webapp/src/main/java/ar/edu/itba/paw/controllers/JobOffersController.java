package ar.edu.itba.paw.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
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
	private UserService userService;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public ModelAndView jobOffers() {
		final ModelAndView mav = new ModelAndView("job_offers/index");
		mav.addObject("job_offers", jobOfferService.all());
		return mav;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView getJobOffer(@PathVariable final Long id) {
		final ModelAndView mav = new ModelAndView("job_offers/show");
		mav.addObject("job", jobOfferService.find(id));
		mav.addObject("userApply", new User());
		java.util.List<JobApplication> applications = jobApplicationService.jobOfferApplications(id);
		mav.addObject("quantityApplications", applications != null ? applications.size() : 0);
		return mav;
	}

	@RequestMapping(path = "/{id}/apply", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView applyJobOfferByEmail(@PathVariable final Long id, @ModelAttribute("userApply") User userApply,
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
	public ModelAndView showJobOfferForm() {
		final ModelAndView mav = new ModelAndView("job_offers/create");
		JobOffer jobOffer = new JobOffer();
		mav.addObject("jobOffer", jobOffer);
		return mav;
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView addJobOffer(@ModelAttribute("jobOffer") JobOffer jobOffer, Map<String, Object> model) {
		jobOfferService.create(jobOffer.getTitle(), jobOffer.getDescription(), jobOffer.getUserId());
		return jobOffers();
	}
}
