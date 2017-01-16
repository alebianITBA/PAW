package ar.edu.itba.paw.api.v1.controllers;import ar.edu.itba.paw.api.v1.dto.PostDTO;import ar.edu.itba.paw.api.v1.parameters.PostParams;import ar.edu.itba.paw.helpers.PaginationHelper;import ar.edu.itba.paw.interfaces.PostService;import ar.edu.itba.paw.models.Post;import ar.edu.itba.paw.utils.Pair;import ar.edu.itba.paw.validators.PostValidator;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;import javax.ws.rs.*;import javax.ws.rs.core.GenericEntity;import javax.ws.rs.core.MediaType;import javax.ws.rs.core.Response;import java.util.List;@Path("api/v1/posts")@Componentpublic class PostsController extends ApiController {  @Autowired  private PostService postService;  @GET  @Path("/{id}")  public Response show(@PathParam("id") final long id) {    final Post post = postService.find(id);    if (post != null) {      return ok(new PostDTO(post));    } else {      return notFound();    }  }  @GET  public Response index(@QueryParam("page") Integer pageParam) {    final List<Post> allPosts = postService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE);    GenericEntity<List<PostDTO>> list = new GenericEntity<List<PostDTO>>(PostDTO.fromList(allPosts)) {};    return ok(list);  }  @POST  @Consumes(MediaType.APPLICATION_JSON)  public Response create(final PostParams input) {    Pair<Boolean, String> validation = PostValidator.validate(input);    if (!validation.getLeft()) {      return badRequest(validation.getRight());    }    Post post = postService.create(input.title, input.description, getLoggedUser());    return created(new PostDTO(post));  }  @PUT  @Path("/{id}")  public Response edit(@PathParam("id") final long id, final PostParams input) {    Post post = postService.find(id);    if (post.getUser().getId() != getLoggedUser().getId()) {      return forbidden();    }    Pair<Boolean, String> validation = PostValidator.validate(input);    if (!validation.getLeft()) {      return badRequest(validation.getRight());    }    post = postService.update(id, input.title, input.description);    return ok(new PostDTO(post));  }  @DELETE  @Path("/{id}")  public Response destroy(@PathParam("id") final long id) {    Post post = postService.find(id);    if (post.getUser().getId() != getLoggedUser().getId()) {      return forbidden();    }    postService.delete(id);    return ok();  }}