package ar.edu.itba.paw.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

@XmlRootElement()
public class JobOfferDTO {
	
	@XmlElement(name="id")
	private Long id;
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="user")
	private UserDTO user;
	
	@XmlElement(name="skills")
	private List<Skill> skills;
	
	@XmlElement(name="created_at")
	private Date created_at;
	
	@XmlElement(name="closed_at")
	private Date closed_at;

	public JobOfferDTO() {}
	
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
		List<JobOfferDTO> answer = new ArrayList<JobOfferDTO>();
		for (JobOffer offer : offers) {
			answer.add(new JobOfferDTO(offer));
		}
		return answer;
	}

}
