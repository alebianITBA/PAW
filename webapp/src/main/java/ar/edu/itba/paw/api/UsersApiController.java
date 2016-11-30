package ar.edu.itba.paw.api;

import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.itba.paw.api.dto.UserDTO;
import ar.edu.itba.paw.api.parameters.UserRegistration;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.utils.Pair;
import ar.edu.itba.paw.validators.UserPasswordValidator;

@Path("api/v1/users")
@Component
public class UsersApiController {
	
	@Autowired
    private UserService userService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers(@RequestParam(required = false, value = "page") final Integer pageParam) {
        final List<User> allUsers = userService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE);

        return Response.ok(UserDTO.fromList(allUsers)).build();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
    @Produces(value = { MediaType.APPLICATION_JSON })
    public Response getById(@PathParam("id") final long id) {
        final User user = userService.find(id);

        if (user != null) {
            return Response.ok(new UserDTO(user)).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
