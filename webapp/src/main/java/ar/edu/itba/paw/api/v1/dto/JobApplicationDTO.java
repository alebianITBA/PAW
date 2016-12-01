package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.JobApplication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class JobApplicationDTO {

  @XmlElement(name = "id")
  private Long id;

  @XmlElement(name = "description")
  private String description;

  @XmlElement(name = "user")
  private UserDTO user;

  @XmlElement(name = "job_offer")
  private JobOfferDTO jobOffer;

  @XmlElement(name = "created_at")
  private Date created_at;

  public JobApplicationDTO() {
  }

  public JobApplicationDTO(JobApplication application) {
    this.id = application.getId();
    this.description = application.getDescription();
    this.user = new UserDTO(application.getUser());
    this.jobOffer = new JobOfferDTO(application.getJobOffer());
    this.created_at = application.getCreatedAt();
  }

  public static List<JobApplicationDTO> fromList(List<JobApplication> applications) {
    return applications.stream().map(a -> new JobApplicationDTO(a)).collect(Collectors.toList());
  }

}
