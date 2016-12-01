package ar.edu.itba.paw.api.v1.controllers;

import javax.json.Json;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
public class ApiController {
  final static String USER_DOES_NOT_EXIST = "User does not exist.";

  Response ok() {
    return Response.ok().build();
  }

  Response ok(final Object object) {
    return Response.ok(object).build();
  }

  Response created(final Object entity) {
    return Response.status(Response.Status.CREATED).entity(entity).build();
  }

  Response badRequest(final String msg) {
    return Response.status(Response.Status.BAD_REQUEST).entity(Json.createObjectBuilder().add("errors", msg)).build();
  }

  Response notFound() {
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  Response notFound(final String msg) {
    return Response.status(Response.Status.NOT_FOUND).entity(Json.createObjectBuilder().add("errors", msg)).build();
  }
}
