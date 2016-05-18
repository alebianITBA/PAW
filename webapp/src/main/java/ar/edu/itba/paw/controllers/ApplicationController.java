package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Controller
public class ApplicationController {

  @Autowired
  private UserService userService;

  @ModelAttribute("loggedUser")
  protected User getLoggedUser() {
    return userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
  }

}
