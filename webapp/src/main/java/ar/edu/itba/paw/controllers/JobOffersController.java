package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;

@Controller
@RequestMapping("/job_offers")
public class JobOffersController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private JobApplicationService jobApplicationService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView jobOffers() {
		final ModelAndView mav = new ModelAndView("job_offers/index");
		mav.addObject("job_offers", jobOfferService.all());
		return mav;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView getJobOffer(@PathVariable final Long id) {
		final ModelAndView mav = new ModelAndView("job_offers/show");
		mav.addObject("job", jobOfferService.find(id));
		mav.addObject("applications", jobApplicationService.jobOfferApplications(id));
		return mav;
	}

}
