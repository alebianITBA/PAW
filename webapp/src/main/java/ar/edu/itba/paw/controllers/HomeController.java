package ar.edu.itba.paw.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.config.LoggedUser;
import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Skill;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView home(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult errors) {
		final ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("loggedUser", LoggedUser.getLoggedUser(SecurityContextHolder.getContext(), userService));
		mav.addObject("posts", postService.all(1, 50));
		List<Skill> userSkills = null; // TODO Get the logged user skills
		mav.addObject("offers", jobOfferService.withSkills(userSkills, 1, 10));
		return mav;
	}

}
