package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.ErrorCodes;
import ar.edu.itba.paw.api.v1.dto.ErrorDTO;
import ar.edu.itba.paw.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class ApiController {
  protected static final Logger logger = LoggerFactory.getLogger(ApiController.class);

  protected User getLoggedUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  Response ok() {
    return Response.ok().build();
  }

  Response ok(final Object entity) {
    return Response.ok(entity).build();
  }

  Response created() {
    return Response.status(Response.Status.CREATED).build();
  }

  Response created(final Object entity) {
    return Response.status(Response.Status.CREATED).entity(entity).build();
  }

  Response badRequest(final ErrorCodes error) {
    return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(error)).build();
  }

  Response notFound() {
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  Response notFound(final ErrorCodes error) {
    return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(error)).build();
  }

  Response unauthorized() {
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }

  Response unauthorized(final ErrorCodes error) {
    return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDTO(error)).build();
  }

  Response forbidden() {
    return Response.status(Response.Status.FORBIDDEN).build();
  }

  Response forbidden(final ErrorCodes error) {
    return Response.status(Response.Status.FORBIDDEN).entity(new ErrorDTO(error)).build();
  }
}
