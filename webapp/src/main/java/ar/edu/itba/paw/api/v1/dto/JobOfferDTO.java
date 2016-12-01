package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class JobOfferDTO {

  @XmlElement(name = "id")
  private Long id;

  @XmlElement(name = "title")
  private String title;

  @XmlElement(name = "description")
  private String description;

  @XmlElement(name = "user")
  private UserDTO user;

  @XmlElement(name = "skills")
  private List<Skill> skills;

  @XmlElement(name = "created_at")
  private Date created_at;

  @XmlElement(name = "closed_at")
  private Date closed_at;

  public JobOfferDTO() {
  }

  public JobOfferDTO(JobOffer jobOffer) {
    this.id = jobOffer.getId();
    this.title = jobOffer.getTitle();
    this.description = jobOffer.getDescription();
    this.user = new UserDTO(jobOffer.getUser());
    this.created_at = jobOffer.getCreatedAt();
    this.closed_at = jobOffer.getClosedAt();
    this.skills = jobOffer.getSkills();
  }

  public static List<JobOfferDTO> fromList(List<JobOffer> offers) {
    return offers.stream().map(o -> new JobOfferDTO(o)).collect(Collectors.toList());
  }

}
