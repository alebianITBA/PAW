package ar.edu.itba.paw.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.validators.PasswordValidator;

@Controller
@RequestMapping("/")
public class HomeController extends ApplicationController {

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
		mav.addObject("loggedUser", getLoggedUser());
		mav.addObject("posts", postService.all(1, 50));
		mav.addObject("offers", jobOfferService.withSkills(getLoggedUser().getSkills(), 1, 10));
		return mav;
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ModelAndView createUser(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult errors) {

		PasswordValidator passwordValidator = new PasswordValidator();
		passwordValidator.validate(registerForm, errors);

		if (errors.hasErrors()) {
			return home(registerForm, errors);
		} else {
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			//String hashedPassword = passwordEncoder.encode(registerForm.getPassword());

			userService.create(registerForm.getFirstName(), registerForm.getLastName(), registerForm.getEmail(),
					registerForm.getPassword());
			
			return new ModelAndView("redirect:/index");
		}
	}

}
