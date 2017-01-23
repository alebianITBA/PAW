package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.interfaces.JobApplicationService;
import ar.edu.itba.paw.models.JobApplication;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class JobOfferDTO {
  @XmlElement
  private Long id;

  @XmlElement
  private String title;

  @XmlElement
  private String description;

  @XmlElement
  private UserDTO user;

  @XmlElement
  private List<SkillDTO> skills;

  @XmlElement
  private Date created_at;

  @XmlElement
  private Date closed_at;

  @XmlElement
  private boolean user_applied;

  @XmlElement
  private Integer applications;

  public JobOfferDTO() {
  }

  public JobOfferDTO(final JobOffer jobOffer) {
    this.id = jobOffer.getId();
    this.title = jobOffer.getTitle();
    this.description = jobOffer.getDescription();
    this.user = new UserDTO(jobOffer.getUser());
    this.created_at = jobOffer.getCreatedAt();
    this.closed_at = jobOffer.getClosedAt();
    this.skills = SkillDTO.fromList(jobOffer.getSkills());
  }

  public JobOfferDTO(final JobOffer jobOffer, final List<JobApplication> applications, final User user) {
    this.id = jobOffer.getId();
    this.title = jobOffer.getTitle();
    this.description = jobOffer.getDescription();
    this.user = new UserDTO(jobOffer.getUser());
    this.created_at = jobOffer.getCreatedAt();
    this.closed_at = jobOffer.getClosedAt();
    this.skills = SkillDTO.fromList(jobOffer.getSkills());
    this.user_applied = userApplied(jobOffer, applications, user);
    this.applications = applications.size();
  }

  private boolean userApplied(JobOffer jobOffer, List<JobApplication> applications, User user) {
    boolean alreadyApplied = false;
    for (JobApplication application : applications) {
      if (application.getUser().getId() == user.getId()) {
        alreadyApplied = true;
        break;
      }
    }
    return alreadyApplied;
  }

  public static List<JobOfferDTO> fromList(final List<JobOffer> offers, final JobApplicationService service, final User user) {
    return offers.stream().map(o -> new JobOfferDTO(o, service.jobOfferApplications(o.getId()), user)).collect(Collectors.toList());
  }
}
