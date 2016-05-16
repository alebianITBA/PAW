package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.config.UserDetailsServiceImpl;
import ar.edu.itba.paw.dto.JobOfferDTO;
import ar.edu.itba.paw.enums.JobOfferStatus;
import ar.edu.itba.paw.forms.PostForm;
import ar.edu.itba.paw.forms.RegisterForm;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.PostService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.validators.PasswordValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController extends ApplicationController {

  @Autowired
  @Qualifier("authenticationManager")
  private AuthenticationManager authenticationManager;

  @Autowired
    private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private JobOfferService jobOfferService;

  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private JobApplicationService jobApplicationService;

  @RequestMapping(path = "", method = RequestMethod.GET)
  public ModelAndView home(@ModelAttribute("registerForm") final RegisterForm registerForm,
      final BindingResult errors) {
    final ModelAndView mav = new ModelAndView("register");
    return mav;
  }

  @RequestMapping(path = "", method = RequestMethod.POST)
  public ModelAndView homePost() {
    final ModelAndView mav = new ModelAndView("register");
    return mav;
  }

  @RequestMapping(path = "/index", method = RequestMethod.POST)
  public String indexPost() {
    return "redirect:/index";
  }

  @RequestMapping(path = "/index", method = RequestMethod.GET)
  public ModelAndView index(@ModelAttribute("postForm") final PostForm postForm, final BindingResult errors) {
    final ModelAndView mav = new ModelAndView("index");
    mav.addAllObjects(getIndexAttributes());
    return mav;
  }

  @RequestMapping(path = "/create_post", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public ModelAndView createPost(@Valid @ModelAttribute("postForm") final PostForm postForm,
      final BindingResult errors) {
    if (!errors.hasErrors()) {
      postService.create(postForm.getTitle(), postForm.getDescription(), getLoggedUser().getId());
      return getIndexView();
    } else {
      return index(postForm, errors);
    }
  }

  @RequestMapping(path = "/create_user", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public ModelAndView createUser(@Valid @ModelAttribute("registerForm") final RegisterForm registerForm,
      final BindingResult errors, HttpServletRequest request, HttpServletResponse response) {

    PasswordValidator passwordValidator = new PasswordValidator();
    passwordValidator.validate(registerForm, errors);

    if (errors.hasErrors()) {
      return home(registerForm, errors);
    } else {

      userService.create(registerForm.getFirstName(), registerForm.getLastName(), registerForm.getEmail(),
          registerForm.getPassword());

      UserDetails userDetails = userDetailsService.loadUserByUsername(registerForm.getEmail());

      try {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        SecurityContextHolder.getContext().setAuthentication(null);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, registerForm.getPassword(), userDetails.getAuthorities());
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
          SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
          return getIndexView();
            }
      } catch (Exception ex) {

      }

      return new ModelAndView("redirect:/index");
    }
  }

  private ModelAndView getIndexView() {
    ModelAndView index = new ModelAndView("/index");
    index.addAllObjects(getIndexAttributes());
    return index;
  }

  private Map<String, Object> getIndexAttributes() {
    User loggedUser = getLoggedUser();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("postForm", new PostForm());
    map.put("loggedUser", loggedUser);
    map.put("posts", postService.all(1, 50));

    List<JobOffer> jobOfferList = jobOfferService.withSkills(getLoggedUser().getSkills(), 1, 10);
    jobOfferList.subList(0, Math.max(jobOfferList.size(), (int) Math.floor(Math.random() * jobOfferList.size())));

    List<JobApplication> alreadyApplies = jobApplicationService.userJobApplications(loggedUser.getId());
    List<JobOfferDTO> jobOfferListDTO = new ArrayList<JobOfferDTO>();
    // Deberia ir a un helper
    for (JobOffer offer : jobOfferList) {
      JobOfferDTO offerDTO = JobOfferDTO.fromModel(offer);
      if (offerDTO.getUserId() == loggedUser.getId()) {
        offerDTO.setStatus(JobOfferStatus.OFFER_OWNER);
      } else {
        boolean alreadyApply = false;
        for (JobApplication application : alreadyApplies) {
          if (application.getJobOfferId() == offerDTO.getId()) {
            alreadyApply = true;
            break;
          }
        }

        if (alreadyApply) {
          offerDTO.setStatus(JobOfferStatus.ALREADY_APPLIED);
        } else {
          offerDTO.setStatus(JobOfferStatus.READY_TO_APPLY);
        }
      }
      jobOfferListDTO.add(offerDTO);
    }

    map.put("offers", jobOfferListDTO);

    return map;
  }

}
