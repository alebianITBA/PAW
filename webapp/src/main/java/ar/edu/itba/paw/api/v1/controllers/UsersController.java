package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.UserDTO;
import ar.edu.itba.paw.api.v1.parameters.UserEdit;
import ar.edu.itba.paw.api.v1.parameters.UserRegistration;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.utils.Pair;
import ar.edu.itba.paw.validators.UserPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("api/v1/users")
@Component
public class UsersController extends ApiController {

  @Autowired
  private UserService userService;

  @GET
  public Response listUsers(@PathParam("page") Integer pageParam) {
    final List<User> allUsers = userService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE);
    GenericEntity<List<UserDTO>> list = new GenericEntity<List<UserDTO>>(UserDTO.fromList(allUsers)) {};
    return Response.ok(list).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response register(final UserRegistration input) {
    User existUser = userService.findByEmail(input.email);
    if (existUser != null) {
      return Response.status(Status.BAD_REQUEST).build();
    } else {
      Pair<Boolean, String> validation = UserPasswordValidator.validate(input.password, input.passwordConfirmation);

      if (validation.getLeft() == false) {
        return Response.status(Status.BAD_REQUEST)
          .entity(Json.createObjectBuilder().add("errors", validation.getRight()).build().toString())
          .build();
      }

      User user = userService.create(input.firstName, input.lastName, input.email, input.password);

      if (user.getId() == null) {
        return Response.status(Status.BAD_REQUEST)
          .entity(Json.createObjectBuilder().add("errors", "User already exists."))
          .build();
      }

      return Response.status(Status.CREATED).entity(user).build();
    }
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") final long id) {
    final User user = userService.find(id);

    if (user != null) {
      return Response.ok(new UserDTO(user)).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @GET
  @Path("/me")
  public Response me() {
    // TODO: do this when authorization is set
    return Response.ok().build();
  }

  @PUT
  @Path("/{id}")
  public Response edit(@PathParam("id") final long id, final UserEdit input) {
    // TODO: change this to /me when authorization is set
    User user = userService.update(id, input.firstName, input.lastName, input.skillIds);
    return Response.ok(new UserDTO(user)).build();
  }
}
