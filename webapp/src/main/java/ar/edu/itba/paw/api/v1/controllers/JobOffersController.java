package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.JobOfferDTO;
import ar.edu.itba.paw.api.v1.parameters.JobOfferParams;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.utils.Pair;
import ar.edu.itba.paw.validators.JobOfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("api/v1/job_offers")
@Component
public class JobOffersController extends ApiController {
  @Autowired
  private JobOfferService jobOfferService;

  @Autowired
  private JobApplicationService jobApplicationService;

  @GET
  @Path("/{id}")
  public Response show(@PathParam("id") final long id) {
    final JobOffer jobOffer = jobOfferService.find(id);

    if (jobOffer != null) {
      return ok(new JobOfferDTO(jobOffer, jobApplicationService.jobOfferApplications(jobOffer.getId()), getLoggedUser()));
    } else {
      return notFound();
    }
  }

  @GET
  public Response index(@QueryParam("page") Integer pageParam) {
    // TODO: Add parameter for filter
    final List<JobOffer> allJobOffers = jobOfferService.all(PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.DEFAULT_PER_PAGE);
    GenericEntity<List<JobOfferDTO>> list = new GenericEntity<List<JobOfferDTO>>(JobOfferDTO.fromList(allJobOffers, jobApplicationService, getLoggedUser())) {
    };
    return ok(list);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(final JobOfferParams input) {
    Pair<Boolean, String> validation = JobOfferValidator.validate(input);
    if (!validation.getLeft()) {
      return badRequest(validation.getRight());
    }

    // TODO: there is an error when creating an offer with same ids
    // TODO: skillIds should be an array [1,2] not a "1,2"
    JobOffer jobOffer = jobOfferService.create(input.title, input.description, getLoggedUser(), input.skillIds);
    return created(new JobOfferDTO(jobOffer));
  }

  @PUT
  @Path("/{id}")
  public Response edit(@PathParam("id") final long id, final JobOfferParams input) {
    JobOffer jobOffer = jobOfferService.find(id);
    if (jobOffer.getUser().getId() != getLoggedUser().getId()) {
      return forbidden();
    }

    Pair<Boolean, String> validation = JobOfferValidator.validate(input);
    if (!validation.getLeft()) {
      return badRequest(validation.getRight());
    }

    jobOffer = jobOfferService.update(id, input.title, input.description);
    return ok(new JobOfferDTO(jobOffer));
  }

  @DELETE
  @Path("/{id}")
  public Response destroy(@PathParam("id") final long id) {
    JobOffer jobOffer = jobOfferService.find(id);
    if (jobOffer.getUser().getId() != getLoggedUser().getId()) {
      return forbidden();
    }

    jobOfferService.delete(id);
    return ok();
  }

  @PUT
  @Path("/{id}/open")
  public Response open(@PathParam("id") final long id) {
    JobOffer jobOffer = jobOfferService.find(id);
    if (jobOffer.getUser().getId() != getLoggedUser().getId()) {
      return forbidden();
    }

    jobOffer = jobOfferService.update(jobOffer.getId(), null);
    return ok(new JobOfferDTO(jobOffer));
  }

  @PUT
  @Path("/{id}/finish")
  public Response finish(@PathParam("id") final long id) {
    JobOffer jobOffer = jobOfferService.find(id);
    if (jobOffer.getUser().getId() != getLoggedUser().getId()) {
      return forbidden();
    }

    jobOffer = jobOfferService.update(jobOffer.getId(), new Date());
    return ok(new JobOfferDTO(jobOffer));
  }
}
