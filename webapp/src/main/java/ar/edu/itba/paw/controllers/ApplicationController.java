package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {

  @Autowired
  private UserService userService;

  protected User getLoggedUser() {
    return userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
  }

}
