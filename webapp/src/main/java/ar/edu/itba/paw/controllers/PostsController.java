package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostsController extends ApplicationController {

  @Autowired
  private PostService postService;

  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(value = HttpStatus.OK)
  public ModelAndView deletePost(@PathVariable final Long id) {
    Post post = postService.find(id);
    if(post.getUserId() == getLoggedUser().getId()) {
      postService.delete(id);
    }
    return new ModelAndView("redirect:/users/me");
  }

}
