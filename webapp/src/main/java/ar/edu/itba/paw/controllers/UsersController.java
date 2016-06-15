package ar.edu.itba.paw.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.itba.paw.forms.UserForm;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@Controller
public class UsersController extends ApplicationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;
	
	@Autowired
	private SkillService skillService;

	@Autowired
	private JobOfferService jobOfferService;
	
	@Autowired
	private JobApplicationService jobApplicationService;

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
	public ModelAndView getUser(@PathVariable final Long id, @ModelAttribute("userForm") final UserForm userForm,
			final BindingResult binding) {
		
		User user = userService.find(id);
		if (user == null) {
			return new ModelAndView("redirect:/not_found");
		}
		
		final ModelAndView mav = new ModelAndView("users/show");
		
		userForm.setId(user.getId());
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		String skillIds = "";
		int i = 0;
		for (Skill skill : user.getSkills()) {
			if (i > 0) {
				skillIds += ",";	
			}
			skillIds += skill.getId();
			i++;
		}
		userForm.setSelectedSkillIds(skillIds);
		
		mav.addObject("userForm", userForm);
		mav.addObject("user", user);
		mav.addObject("posts", postService.userPosts(id));
		mav.addObject("offers", jobOfferService.userJobOffers(id));
		mav.addObject("skills", skillService.all());
		
		if(getLoggedUser().getId().equals(id)){
			mav.addObject("offersApplied", jobApplicationService.userJobApplications(id));
		}
		
		return mav;
	}

	@RequestMapping(path = "/users/me", method = RequestMethod.GET)
	public String me() {
		return "forward:/users/" + getLoggedUser().getId().toString();
	}
	
	@RequestMapping(path = "/users/{id}", method = RequestMethod.POST)
	public String editUser(@PathVariable final Long id, @Valid @ModelAttribute("userForm") final UserForm userForm,
			final BindingResult binding, RedirectAttributes attr) {
		
		if (getLoggedUser().getId() == id) {
			if (binding.hasErrors()) {
				attr.addFlashAttribute("userForm", userForm);
				LOGGER.info("Binding has errors " + binding.getErrorCount());
				return "redirect:/users/" + getLoggedUser().getId().toString();
			} else {
				User user = userService.update(userForm.getId(), userForm.getFirstName(), userForm.getLastName(), userForm.getSelectedSkillIds());
				LOGGER.info("Updated User: " + user.toString());
			}
		}
		
		return "redirect:/users/" + getLoggedUser().getId().toString();
	}

}
