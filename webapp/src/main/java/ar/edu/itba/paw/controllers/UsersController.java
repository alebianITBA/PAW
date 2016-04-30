package ar.edu.itba.paw.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.validators.PasswordValidator;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView listUser() {
		final ModelAndView mav = new ModelAndView("users/index");
		mav.addObject("users", userService.all());
		return mav;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable final Long id) {
		final ModelAndView mav = new ModelAndView("users/show");
		mav.addObject("user", userService.find(id));
		mav.addObject("posts", postService.userPosts(id));
		mav.addObject("offers", jobOfferService.userJobOffers(id));
		return mav;
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public ModelAndView showRegisterForm(@ModelAttribute("registerForm") final RegisterForm registerForm) {
		return new ModelAndView("users/register");
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView addUser(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult errors) {

		// Aca valido que las passwords sean iguales.
		// Podemos hacer un validator que busque los mails en la BD y sean
		// unicos.
		PasswordValidator passwordValidator = new PasswordValidator();
		passwordValidator.validate(registerForm, errors);

		if (errors.hasErrors()) {
			return showRegisterForm(registerForm);
		} else {
			/*
			 * TODO: registerForm.getUser() o convertir en algun lugar. Para
			 * Hibernate, va a servir asi hacemos user.save()
			 */

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(registerForm.getPassword());

			userService.create(registerForm.getFirstName(), registerForm.getLastName(), registerForm.getEmail(),
					hashedPassword);
			return listUser();
		}
	}

}
