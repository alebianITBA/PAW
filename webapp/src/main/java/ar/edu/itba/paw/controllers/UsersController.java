package ar.edu.itba.paw.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.validators.PasswordValidator;

@Controller
public class UsersController extends ApplicationController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public ModelAndView listUser(@RequestParam(required = false, value = "page") final Integer pageParam) {
		
		final ModelAndView mav = new ModelAndView("users/index");
		
		mav.addObject("users",
				userService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE));
		mav.addObject("item_count", userService.count());
		
		return mav;
	}

	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable final Long id) {
		
		User user = userService.find(id);
		if (user == null) {
			return new ModelAndView("redirect:/not_found");
		}
		
		final ModelAndView mav = new ModelAndView("users/show");
		
		mav.addObject("user", user);
		mav.addObject("posts", postService.userPosts(id));
		mav.addObject("offers", jobOfferService.userJobOffers(id));
		
		return mav;
	}

	@RequestMapping(path = "/users/me", method = RequestMethod.GET)
	public String me() {
		
		return "forward:/users/" + getLoggedUser().getId().toString();
	}

	@RequestMapping(path = "/users", method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult binding, HttpServletRequest request, RedirectAttributes attr) {

		PasswordValidator passwordValidator = new PasswordValidator();
		passwordValidator.validate(registerForm, binding);

		if (binding.hasErrors()) {
			attr.addFlashAttribute("registerForm", registerForm);
			return "redirect:/";
		} else {

			userService.create(registerForm.getFirstName(), registerForm.getLastName(), registerForm.getEmail(),
					registerForm.getPassword());

			UserDetails userDetails = userDetailsService.loadUserByUsername(registerForm.getEmail());

			try {
				// TODO: See if this line is useful, else remove it
				SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
				SecurityContextHolder.getContext().setAuthentication(null);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
						registerForm.getPassword(), userDetails.getAuthorities());
				request.getSession();
				token.setDetails(new WebAuthenticationDetails(request));
				Authentication authenticatedUser = authenticationManager.authenticate(token);
				if (token.isAuthenticated()) {
					SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
				}
			} catch (Exception ex) {
			}

			return "redirect:/index";
		}
	}

}
