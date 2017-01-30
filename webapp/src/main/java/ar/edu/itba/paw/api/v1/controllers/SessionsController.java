package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.ErrorCodes;
import ar.edu.itba.paw.api.v1.dto.TokenDTO;
import ar.edu.itba.paw.api.v1.parameters.UserParams;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("v1")
@Component
public class SessionsController extends ApiController {
  @Autowired
  UserService userService;

  @POST
  @Path("/login")
  public Response login(final UserParams input) {
    User user = userService.findByEmail(input.email);
    if (user != null && user.getPassword().equals(input.password)) {
      return ok(new TokenDTO(user));
    }
    return unauthorized(ErrorCodes.LOGIN_ERROR);
  }
}
