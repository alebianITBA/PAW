package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;

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
		mav.addObject("count", userService.count());
		return mav;
	}

	@RequestMapping(path = "/create/{email}", method = RequestMethod.GET)
	public ModelAndView createUser(@RequestParam(required = true, value = "password") final String password,
			@PathVariable(value = "email") final String email) {
		final ModelAndView mav = new ModelAndView("users/create");
		userService.create(email, password);
		User user = userService.findByEmail(email);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(path = "delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable final Long id) {
		final ModelAndView mav = new ModelAndView("users/delete");
		userService.delete(id);
		mav.addObject("id", id);
		mav.addObject("count", userService.count());
		return mav;
	}

	@RequestMapping(path = "update/{id}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable final Long id,
			@RequestParam(required = false, value = "first_name") final String first_name,
			@RequestParam(required = false, value = "last_name") final String last_name,
			@RequestParam(required = false, value = "email") final String email,
			@RequestParam(required = false, value = "password") final String password) {
		final ModelAndView mav = new ModelAndView("users/update");
		userService.update(id, first_name, last_name, email, password);
		return mav;
	}

}
