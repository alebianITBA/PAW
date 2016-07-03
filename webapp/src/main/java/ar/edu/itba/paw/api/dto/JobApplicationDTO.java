package ar.edu.itba.paw.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.edu.itba.paw.models.JobApplication;

@XmlRootElement()
public class JobApplicationDTO {
	
	@XmlElement(name="id")
	private Long id;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="user")
	private UserDTO user;
	
	@XmlElement(name="job_offer")
	private JobOfferDTO jobOffer;
	
	@XmlElement(name="created_at")
	private Date created_at;
	
	public JobApplicationDTO() {}
	
	public JobApplicationDTO(JobApplication application) {
		this.id = application.getId();
		this.description = application.getDescription();
		this.user = new UserDTO(application.getUser());
		this.jobOffer = new JobOfferDTO(application.getJobOffer());
		this.created_at = application.getCreatedAt();
	}
	
	public static List<JobApplicationDTO> fromList(List<JobApplication> applications) {
		List<JobApplicationDTO> answer = new ArrayList<JobApplicationDTO>();
		for (JobApplication application : applications) {
			answer.add(new JobApplicationDTO(application));
		}
		return answer;
	}

}
