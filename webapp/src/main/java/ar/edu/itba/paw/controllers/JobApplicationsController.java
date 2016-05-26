package ar.edu.itba.paw.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.models.JobApplication;

@Controller
public class JobApplicationsController extends ApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobApplicationsController.class);

	@Autowired
	private JobApplicationService jobApplicationService;

	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(path = "/job_offers/{id}/apply", method = RequestMethod.POST)
	public String applyJobOffer(@PathVariable final Long id) {

		jobApplicationService.create("Basic application", getLoggedUser(), jobOfferService.find(id));
		LOGGER.info("New Application of User " + getLoggedUser().toString() + " to Job Offer "
				+ id.toString());

		return "redirect:/job_offers";
	}

	@RequestMapping(path = "/job_application/{id}", method = RequestMethod.DELETE)
	public String deleteJobApplication(@PathVariable final Long id) {

		JobApplication application = jobApplicationService.find(id);
		if (application.getUser().getId() == getLoggedUser().getId()) {
			jobApplicationService.delete(id);
			LOGGER.info("Deleted Application of User " + getLoggedUser().toString() + " from Job Offer "
					+ application.getJobOffer().toString());
		}

		return "redirect:/users/me";
	}

}
