package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.JobApplicationDTO;
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
  @Path("/me")
  public Response myApplications() {
    final List<JobApplication> allApplications = jobApplicationService.userJobApplications(getLoggedUser().getId());
    GenericEntity<List<JobApplicationDTO>> list = new GenericEntity<List<JobApplicationDTO>>(JobApplicationDTO.fromList(allApplications)) {
    };
    return ok(list);
  }

  @POST
  public Response create(@QueryParam("job_offer_id") final long job_offer_id) {
    JobOffer jobOffer = jobOfferService.find(job_offer_id);
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
