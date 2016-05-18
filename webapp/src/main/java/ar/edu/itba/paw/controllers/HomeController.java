package ar.edu.itba.paw.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.dto.JobOfferDTO;
import ar.edu.itba.paw.enums.JobOfferStatus;
import ar.edu.itba.paw.forms.PostForm;
import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;

@Controller
@RequestMapping("/")
public class HomeController extends ApplicationController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private PostService postService;

	@Autowired
	private JobApplicationService jobApplicationService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult binding) {
		
		final ModelAndView mav = new ModelAndView("register");
		
		mav.addObject("registerForm", registerForm);
		// TODO: This doesn't work
		mav.addObject("errors", binding);
		
		return mav;
	}
	
	@RequestMapping(path = "/not_found", method = RequestMethod.GET)
	public String notFound() {
		return "404";
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("postForm") final PostForm postForm, final BindingResult binding) {
		final ModelAndView mav = new ModelAndView("index");
		mav.addAllObjects(getIndexAttributes());
		mav.addObject("postForm", postForm);
		// TODO: This doesn't work
		mav.addObject("errors", binding);
		return mav;
	}

	// TODO: put this somewhere else
	private Map<String, Object> getIndexAttributes() {
		User loggedUser = getLoggedUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postForm", new PostForm());
		map.put("loggedUser", loggedUser);
		map.put("posts", postService.all());

		List<JobOffer> jobOfferList = jobOfferService.notFromUser(getLoggedUser().getId(), 1, 10);
		jobOfferList.subList(0, Math.max(jobOfferList.size(), (int) Math.floor(Math.random() * jobOfferList.size())));

		List<JobApplication> alreadyApplies = jobApplicationService.userJobApplications(loggedUser.getId());
		List<JobOfferDTO> jobOfferListDTO = new ArrayList<JobOfferDTO>();
		// Deberia ir a un helper
		for (JobOffer offer : jobOfferList) {
			JobOfferDTO offerDTO = JobOfferDTO.fromModel(offer);
			if (offerDTO.getUserId() == loggedUser.getId()) {
				offerDTO.setStatus(JobOfferStatus.OFFER_OWNER);
			} else {
				boolean alreadyApply = false;
				for (JobApplication application : alreadyApplies) {
					if (application.getJobOffer().getId() == offerDTO.getId()) {
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

		map.put("offers", jobOfferListDTO);

		return map;
	}

}
