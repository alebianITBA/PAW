package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.ErrorCodes;
import ar.edu.itba.paw.api.v1.dto.JobOfferDTO;
import ar.edu.itba.paw.api.v1.dto.UserDTO;
import ar.edu.itba.paw.api.v1.parameters.SkillParams;
import ar.edu.itba.paw.api.v1.parameters.UserParams;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.utils.Pair;
import ar.edu.itba.paw.validators.UserPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("v1/users")
@Component
public class UsersController extends ApiController {

  @Autowired
  private UserService userService;

  @Autowired
  private SkillService skillService;

  @Autowired
  private JobOfferService jobOfferService;

  @Autowired
  private JobApplicationService jobApplicationService;

  @GET
  public Response index(@QueryParam("page") Integer pageParam) {
    final List<User> allUsers = userService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE);
    GenericEntity<List<UserDTO>> list = new GenericEntity<List<UserDTO>>(UserDTO.fromList(allUsers)) {
    };
    return ok(list);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(final UserParams input) {
    User existUser = userService.findByEmail(input.email);
    if (existUser != null) {
      return badRequest(ErrorCodes.EMAIL_ALREADY_IN_USE);
    } else {
      Pair<Boolean, ErrorCodes> validation = UserPasswordValidator.validate(input.password, input.passwordConfirmation);

      if (!validation.getLeft()) {
        return badRequest(validation.getRight());
      }

      User user = userService.create(input.firstName, input.lastName, input.email, input.password);

      if (user.getId() == null) {
        return badRequest(ErrorCodes.DATABASE_ERROR);
      }

      return created(user);
    }
  }

  @GET
  @Path("/{id}")
  public Response show(@PathParam("id") final long id) {
    final User user = userService.find(id);

    if (user != null) {
      return ok(new UserDTO(user));
    } else {
      return notFound();
    }
  }

  @GET
  @Path("/{id}/job_offers")
  public Response userJobOffers(@PathParam("id") final long id, @QueryParam("page") Integer page, @QueryParam("per_page") Integer perPage) {
    final User user = userService.find(id);

    if (user != null) {
      List<JobOffer> offers = jobOfferService.userJobOffers(id, PaginationHelper.INSTANCE.page(page), PaginationHelper.INSTANCE.perPage(perPage));
      GenericEntity<List<JobOfferDTO>> list = new GenericEntity<List<JobOfferDTO>>(JobOfferDTO.fromList(offers, jobApplicationService, getLoggedUser())) {
      };
      return ok(list);
    } else {
      return notFound();
    }
  }

  @GET
  @Path("/me")
  public Response me() {
    return ok(new UserDTO(getLoggedUser()));
  }

  @PUT
  @Path("/me")
  public Response edit(final UserParams input) {
    User user = getLoggedUser();
    user = userService.update(user.getId(), input.firstName, input.lastName, input.skillIds);
    return ok(new UserDTO(user));
  }
}
