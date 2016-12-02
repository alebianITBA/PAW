package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.ErrorDTO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class ApiController {
  final static String USER_DOES_NOT_EXIST = "User does not exist.";

  Response ok() {
    return Response.ok().build();
  }

  Response ok(final Object entity) {
    return Response.ok(entity).build();
  }

  Response created(final Object entity) {
    return Response.status(Response.Status.CREATED).entity(entity).build();
  }

  Response badRequest(final String msg) {
    return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(msg)).build();
  }

  Response notFound() {
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  Response notFound(final String msg) {
    return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(msg)).build();
  }

  Response unauthorized() {
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }

  Response unauthorized(final String msg) {
    return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDTO(msg)).build();
  }
}
