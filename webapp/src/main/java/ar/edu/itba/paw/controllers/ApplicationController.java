package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Controller
public class ApplicationController {

	@Autowired
	private UserService userService;

	protected User loggedUser;

	protected User getLoggedUser() {
		if (loggedUser == null) {
			loggedUser = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		return loggedUser;
	}

}