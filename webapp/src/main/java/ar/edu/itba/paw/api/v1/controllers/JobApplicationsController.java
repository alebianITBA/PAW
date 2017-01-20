package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.JobApplicationDTO;
import ar.edu.itba.paw.api.v1.parameters.JobApplicationParams;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/v1/job_applications")
@Component
public class JobApplicationsController extends ApiController {
  @Autowired
  private JobApplicationService jobApplicationService;

  @Autowired
  private JobOfferService jobOfferService;

  @GET
  public Response myApplications(@QueryParam("page") Integer pageParam, @QueryParam("per_page") Integer perPage) {
    final List<JobApplication> allApplications = jobApplicationService.userJobApplications(getLoggedUser().getId(), PaginationHelper.INSTANCE.page(pageParam), PaginationHelper.INSTANCE.perPage(perPage));
    GenericEntity<List<JobApplicationDTO>> list = new GenericEntity<List<JobApplicationDTO>>(JobApplicationDTO.fromList(allApplications)) {
    };
    return ok(list);
  }

  @POST
  public Response create(final JobApplicationParams input) {
    JobOffer jobOffer = jobOfferService.find(input.jobOfferId);
    if (jobOffer.getUser().getId() == getLoggedUser().getId()) {
      return forbidden();
    }

    jobApplicationService.create("Basic application", getLoggedUser(), jobOffer);
    return created();
  }

  @DELETE
  @Path("/{id}")
  public Response destroy(@PathParam("id") final long id) {
    JobApplication application = jobApplicationService.find(id);
    if (application.getUser().getId() != getLoggedUser().getId()) {
      return forbidden();
    }

    jobApplicationService.delete(id);
    return ok();
  }
}
