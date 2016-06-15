package ar.edu.itba.paw.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.itba.paw.forms.PostForm;
import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.validators.PasswordValidator;

@Controller
@RequestMapping("/")
public class HomeController extends ApplicationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Autowired
	private JobOfferService jobOfferService;

	@Autowired
	private PostService postService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult binding) {

		final ModelAndView mav = new ModelAndView("register");

		mav.addObject("registerForm", registerForm);
		mav.addObject("errors", binding);

		return mav;
	}

	@RequestMapping(path = "/users/register", method = RequestMethod.POST)
	public ModelAndView createUser(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm,
			final BindingResult binding, HttpServletRequest request, RedirectAttributes attr) {

		PasswordValidator passwordValidator = new PasswordValidator();
		passwordValidator.validate(registerForm, binding);

		if (binding.hasErrors()) {
			return home(registerForm, binding);
		} else {

			User existUser = userService.findByEmail(registerForm.getEmail());
			
			if (existUser != null) {
				binding.rejectValue("email", "registerForm.accountExists");
				return home(registerForm, binding);
			} else {
				User user = userService.create(registerForm.getFirstName(), registerForm.getLastName(),
						registerForm.getEmail(), registerForm.getPassword());

				LOGGER.info("Created User: " + user.toString());

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

				return new ModelAndView("redirect:/index");
			}
			
			
		}
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
