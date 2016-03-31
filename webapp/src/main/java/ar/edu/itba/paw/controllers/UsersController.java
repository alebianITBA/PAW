package ar.edu.itba.paw.controllers;

import java.sql.SQLException;

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

	@RequestMapping(path = "/create/{email}", method = RequestMethod.GET)
	public ModelAndView createUser(@RequestParam(required = true, value = "password") final String password,
			@PathVariable(value = "email") final String email) throws SQLException {
		final ModelAndView mav = new ModelAndView("create_user");
		userService.createTable();
		User user = userService.create(email, password);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(path = "/{email}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable final String email) throws SQLException {
		final ModelAndView mav = new ModelAndView("user");
		mav.addObject("user", userService.getByEmail(email));
		return mav;
	}
}
