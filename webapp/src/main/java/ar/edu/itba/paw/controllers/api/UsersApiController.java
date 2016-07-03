package ar.edu.itba.paw.controllers.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Path("v1/users")
@Component
public class UsersApiController {
	
	@Autowired
    private UserService userService;
	
	@GET
    @Path("/")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response listUsers() {
        final List<User> allUsers = userService.all();

        return Response.ok(allUsers).build();
    }
	
	@GET
    @Path("/{id}")
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getById(@PathParam("id") final long id) {
        final User user = userService.find(id);

        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
