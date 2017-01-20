package ar.edu.itba.paw.api.v1.controllers;

import ar.edu.itba.paw.api.v1.dto.JobApplicationDTO;
import ar.edu.itba.paw.api.v1.dto.JobOfferDTO;
import ar.edu.itba.paw.api.v1.parameters.JobOfferParams;
import ar.edu.itba.paw.api.v1.parameters.SkillParams;
import ar.edu.itba.paw.helpers.PaginationHelper;
import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.interfaces.JobOfferService;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.utils.Pair;
import ar.edu.itba.paw.validators.JobOfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("api/v1/job_offers")
@Component
public class JobOffersController extends ApiController {
  @Autowired
  private JobOfferService jobOfferService;

  @Autowired
  private JobApplicationService jobApplicationService;

  @Autowired
  private SkillService skillService;

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
  @Path("/{id}/applications")
  public Response applicationsOf(@PathParam("id") final long id) {
    final JobOffer jobOffer = jobOfferService.find(id);
    if (jobOffer.getUser().getId() != getLoggedUser().getId()) {
      return forbidden();
    }
    List<JobApplication> applications = jobApplicationService.jobOfferApplications(id);

    if (applications != null) {
      GenericEntity<List<JobApplicationDTO>> list = new GenericEntity<List<JobApplicationDTO>>(JobApplicationDTO.fromList(applications)) {
      };
      return ok(list);
    } else {
      return notFound();
    }
  }

  @GET
  public Response index(@QueryParam("page") Integer page, @QueryParam("per_page") Integer perPage, @QueryParam("skill_id") Long skillId) {
    Skill skill = skillService.find(skillId);
    List<JobOffer> jobOffers;

    if (skill != null) {
      List<Skill> skills = new ArrayList<>();
      skills.add(skill);
      jobOffers = jobOfferService.withSkills(skills, PaginationHelper.INSTANCE.page(page), PaginationHelper.INSTANCE.perPage(perPage));
    } else {
      jobOffers = jobOfferService.all(PaginationHelper.INSTANCE.page(page), PaginationHelper.INSTANCE.perPage(perPage));
    }

    GenericEntity<List<JobOfferDTO>> list = new GenericEntity<List<JobOfferDTO>>(JobOfferDTO.fromList(jobOffers, jobApplicationService, getLoggedUser())) {
    };
    return ok(list);
  }

  @GET
  @Path("/not_applied")
  public Response notApplied(@QueryParam("page") Integer page, @QueryParam("per_page") Integer perPage) {
    final List<JobOffer> allJobOffers = jobOfferService.notApplied(getLoggedUser().getId(), PaginationHelper.INSTANCE.page(page), PaginationHelper.INSTANCE.perPage(perPage));
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
    JobOffer jobOffer = jobOfferService.create(input.title, input.description, getLoggedUser(), input.skillIds);
    return created(new JobOfferDTO(jobOffer));
  }

  @PUT
  @Path("/{id}")
  public Response edit(@PathParam("id") final long id, final JobOfferParams input) {
    if (!isUserOwner(id)) {
      return forbidden();
    }

    Pair<Boolean, String> validation = JobOfferValidator.validate(input);
    if (!validation.getLeft()) {
      return badRequest(validation.getRight());
    }

    JobOffer jobOffer = jobOfferService.update(id, input.title, input.description);
    return ok(new JobOfferDTO(jobOffer));
  }

  @POST
  @Path("/{id}/add_skill")
  public Response addSkill(@PathParam("id") final long id, final SkillParams skillParams) {
    if (!isUserOwner(id)) {
      return forbidden();
    }
    return ok();
  }

  @DELETE
  @Path("/{id}/remove_skill")
  public Response removeSkill(@PathParam("id") final long id, @QueryParam("skill_id") final long skillId) {
    if (!isUserOwner(id)) {
      return forbidden();
    }
    return ok();
  }

  @DELETE
  @Path("/{id}")
  public Response destroy(@PathParam("id") final long id) {
    if (!isUserOwner(id)) {
      return forbidden();
    }

    jobOfferService.delete(id);
    return ok();
  }

  @PUT
  @Path("/{id}/open")
  public Response open(@PathParam("id") final long id) {
    if (!isUserOwner(id)) {
      return forbidden();
    }

    JobOffer jobOffer = jobOfferService.update(id, null);
    return ok(new JobOfferDTO(jobOffer));
  }

  @PUT
  @Path("/{id}/finish")
  public Response finish(@PathParam("id") final long id) {
    if (!isUserOwner(id)) {
      return forbidden();
    }

    JobOffer jobOffer = jobOfferService.update(id, new Date());
    return ok(new JobOfferDTO(jobOffer));
  }

  private boolean isUserOwner(long id) {
    JobOffer jobOffer = jobOfferService.find(id);
    if (jobOffer.getUser().getId() != getLoggedUser().getId()) {
      return false;
    }
    return true;
  }
}
