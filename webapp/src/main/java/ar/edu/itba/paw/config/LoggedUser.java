package ar.edu.itba.paw.config;

import org.springframework.security.core.context.SecurityContext;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

public class LoggedUser {
	
	public static User getLoggedUser(SecurityContext context, UserService userService) {
		return userService.findByEmail(context.getAuthentication().getName());
	}

}
