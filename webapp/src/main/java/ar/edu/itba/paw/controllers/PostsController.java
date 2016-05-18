package ar.edu.itba.paw.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.itba.paw.forms.PostForm;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.models.Post;

@Controller
public class PostsController extends ApplicationController {

	@Autowired
	private PostService postService;

	@RequestMapping(path = "/posts/{id}", method = RequestMethod.DELETE)
	public ModelAndView deletePost(@PathVariable final Long id) {
		Post post = postService.find(id);
		if (post.getUserId() == getLoggedUser().getId()) {
			postService.delete(id);
		}
		return new ModelAndView("redirect:/users/me");
	}

	@RequestMapping(path = "/posts", method = RequestMethod.POST)
	public String createPost(@Valid @ModelAttribute("postForm") final PostForm postForm, final BindingResult binding,
			RedirectAttributes attr) {
		if (binding.hasErrors()) {
			attr.addFlashAttribute("postForm", postForm);
		} else {
			postService.create(postForm.getTitle(), postForm.getDescription(), getLoggedUser().getId());
		}
		return "redirect:/index";
	}

}
