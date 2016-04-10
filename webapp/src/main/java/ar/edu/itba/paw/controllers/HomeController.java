package ar.edu.itba.paw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ModelAndView jobOffers() {
		final ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}
