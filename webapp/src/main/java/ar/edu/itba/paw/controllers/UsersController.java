package ar.edu.itba.paw.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;

@Controller
@RequestMapping("/users")
public class UsersController extends ApplicationController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView listUser() {
		final ModelAndView mav = new ModelAndView("users/index");
		mav.addObject("loggedUser", getLoggedUser());
		mav.addObject("users", userService.all(1, 50));
		return mav;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable final Long id) {
		final ModelAndView mav = new ModelAndView("users/show");
		mav.addObject("loggedUser", getLoggedUser());
		mav.addObject("user", userService.find(id));
		mav.addObject("posts", postService.userPosts(id));
		mav.addObject("offers", jobOfferService.userJobOffers(id));
		return mav;
	}
	
	@RequestMapping(path = "/me", method = RequestMethod.GET)
	public ModelAndView me() {
		return getUser(getLoggedUser().getId());
	}

	@RequestMapping(path = "/me/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
	    logout();
	    return new ModelAndView("forward:/");
	}
	
}
