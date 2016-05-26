package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;

@Controller
public class JobApplicationsController extends ApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(path = "/job_offers/{id}/apply", method = RequestMethod.POST)
	public String applyJobOffer(@PathVariable final Long id) {
		
		jobApplicationService.create("Basic application", getLoggedUser(), jobOfferService.find(id));
		
		return "redirect:/job_offers";
	}

	@RequestMapping(path = "/job_application/{id}", method = RequestMethod.DELETE)
	public String deleteJobApplication(@PathVariable final Long id) {
		
		JobApplication application = jobApplicationService.find(id);
		if (application.getUser().getId() == getLoggedUser().getId()) {
			jobApplicationService.delete(id);
		}
		
		return "redirect:/users/me";
	}

}
