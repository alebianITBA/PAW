package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.JobApplication;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class JobApplicationDTO {
  @XmlElement
  private Long id;

  @XmlElement
  private JobOfferDTO job_offer;

  @XmlElement
  private Date created_at;

  @XmlElement
  private UserDTO user;

  public JobApplicationDTO() {
  }

  public JobApplicationDTO(JobApplication application) {
    this.id = application.getId();
    this.job_offer = new JobOfferDTO(application.getJobOffer());
    this.created_at = application.getCreatedAt();
    this.user = new UserDTO(application.getUser());
  }

  public static List<JobApplicationDTO> fromList(List<JobApplication> applications) {
    return applications.stream().map(a -> new JobApplicationDTO(a)).collect(Collectors.toList());
  }

}
