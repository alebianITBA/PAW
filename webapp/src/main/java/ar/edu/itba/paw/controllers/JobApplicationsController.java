package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.models.JobApplication;

@Controller
@RequestMapping("/job_applications")
public class JobApplicationsController {

	@Autowired
	private JobApplicationService jobApplicationService;

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	public ModelAndView jobOffers(@RequestParam(required = true, value = "jobOfferId") final Long jobOfferId) {
		final ModelAndView mav = new ModelAndView("job_applications/new");
		mav.addObject("jobOfferId", jobOfferId);
		return mav;
	}

	@RequestMapping(path = "/create", value = "create" , method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ModelAndView getJobOffer(@RequestBody JobApplication jobApplication) {
		jobApplicationService.create(jobApplication.getDescription(), jobApplication.getUserId(),
				jobApplication.getUserId());
		return new ModelAndView("redirect:/job_offers");
	}

}
