package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.forms.PostForm;
import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.User;

@Controller
@RequestMapping("/")
public class HomeController extends ApplicationController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private PostService postService;

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
	public ModelAndView index(@ModelAttribute("postForm") final PostForm postForm,
			@RequestParam(required = false, value = "page") final Integer pageParam, final BindingResult binding) {
		final ModelAndView mav = new ModelAndView("index");

		User user = getLoggedUser();
		
		mav.addObject("item_count", postService.count());
		mav.addObject("posts",
				postService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.INSTANCE.perPage(20)));
		mav.addObject("postForm", postForm);
		mav.addObject("offers", jobOfferService.notAppliedWithSkills(user.getId(), user.getSkills(),
				PaginationHelper.INSTANCE.page(1), PaginationHelper.INSTANCE.perPage(5)));
		// TODO: This doesn't work
		mav.addObject("errors", binding);

		return mav;
	}

}
