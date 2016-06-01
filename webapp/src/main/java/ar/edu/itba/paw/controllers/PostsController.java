package ar.edu.itba.paw.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostsController.class);

	@Autowired
	private PostService postService;

	@RequestMapping(path = "/posts/{id}", method = RequestMethod.DELETE)
	public String deletePost(@PathVariable final Long id) {
		
		Post post = postService.find(id);
		
		if (post.getUser().getId() == getLoggedUser().getId()) {
			postService.delete(id);
			LOGGER.info("Deleted Post with id: " + id.toString());
		}
		
		return "redirect:/users/me";
	}
	
	@RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.GET)
	public ModelAndView editPost(@PathVariable final Long id,
			@ModelAttribute("postForm") PostForm postForm, 
			final BindingResult binding) {

		Post post = postService.find(id);

		if (post == null) {
			return new ModelAndView("redirect:/not_found");
		}

		final ModelAndView mav = new ModelAndView("posts/edit");

		mav.addObject("post", post);
		
		postForm.setId(post.getId());
		postForm.setTitle(post.getTitle());
		postForm.setDescription(post.getDescription());
		mav.addObject("postForm", postForm);

		return mav;
	}


	@RequestMapping(path = "/posts", method = RequestMethod.POST)
	public String createPost(@Valid @ModelAttribute("postForm") final PostForm postForm, final BindingResult binding,
			RedirectAttributes attr) {
			Post post = null;
			if (postForm.getId() != null) {
				if (!binding.hasErrors()) {
					attr.addFlashAttribute("postForm", postForm);
					return "redirect:/posts/edit";
				} else {
					post = postService.update(postForm.getId(), postForm.getTitle(), 
						postForm.getDescription());
					LOGGER.info("Updated Post: " + post.toString());
				}
			} else {
				if (!binding.hasErrors()) {
					attr.addFlashAttribute("postForm", postForm);
				} else {
					post = postService.create(postForm.getTitle(), postForm.getDescription(),
							getLoggedUser());
					LOGGER.info("Created Post: " + post.toString());
				}
			}
			return "redirect:/index";
	}

}
