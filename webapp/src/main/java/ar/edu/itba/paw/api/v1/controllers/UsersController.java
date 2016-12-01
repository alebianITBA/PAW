package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.UserDTO;
import ar.edu.itba.paw.api.v1.parameters.UserParams;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.UserService;
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

@Path("api/v1/users")
@Component
public class UsersController extends ApiController {

  @Autowired
  private UserService userService;

  @GET
  public Response index(@PathParam("page") Integer pageParam) {
    final List<User> allUsers = userService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE);
    GenericEntity<List<UserDTO>> list = new GenericEntity<List<UserDTO>>(UserDTO.fromList(allUsers)) {};
    return ok(list);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(final UserParams input) {
    User existUser = userService.findByEmail(input.email);
    if (existUser != null) {
      return badRequest(USER_DOES_NOT_EXIST);
    } else {
      Pair<Boolean, String> validation = UserPasswordValidator.validate(input.password, input.passwordConfirmation);

      if (!validation.getLeft()) {
        return badRequest(validation.getRight());
      }

      User user = userService.create(input.firstName, input.lastName, input.email, input.password);

      if (user.getId() == null) {
        return badRequest("User already exists.");
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
  @Path("/me")
  public Response me() {
    // TODO: do this when authorization is set
    return ok(null);
  }

  @PUT
  @Path("/{id}")
  public Response edit(@PathParam("id") final long id, final UserParams input) {
    // TODO: change this to /me when authorization is set
    User user = userService.update(id, input.firstName, input.lastName, input.skillIds);
    return ok(new UserDTO(user));
  }
}
