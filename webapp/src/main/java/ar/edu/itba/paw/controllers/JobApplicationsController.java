package ar.edu.itba.paw.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.models.JobApplication;

@Controller
@RequestMapping("/job_applications")
public class JobApplicationsController {

	@Autowired
	private JobApplicationService jobApplicationService;

	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView createJobApplication(@ModelAttribute("jobApplication") JobApplication jobApplication,
			Map<String, Object> model) {
		final ModelAndView mav = new ModelAndView("job_offers/index");
		jobApplicationService.create(jobApplication.getDescription(), jobApplication.getUserId(),
				jobApplication.getJobOfferId());
		mav.addObject("job_offers", jobOfferService.all());
		return mav;
	}

}
