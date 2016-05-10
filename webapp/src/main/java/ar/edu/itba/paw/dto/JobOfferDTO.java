package ar.edu.itba.paw.dto;

import java.util.List;

import ar.edu.itba.paw.enums.JobOfferStatus;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

public class JobOfferDTO {

	private Long id;
	private String title;
	private String description;
	private Long userId;
	private List<Skill> skills;
	private JobOfferStatus status;
	private User user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public String getStatus() {
		return status.getJobOfferStatusEnum();
	}
	public void setStatus(JobOfferStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public static JobOfferDTO fromModel(JobOffer offer) {
		JobOfferDTO dto = new JobOfferDTO();
		
		dto.setId(offer.getId());
		dto.setTitle(offer.getTitle());
		dto.setDescription(offer.getDescription());
		dto.setSkills(offer.getSkills());
		dto.setUserId(offer.getUserId());
		dto.setUser(offer.getUser());
		
		return dto;
	}
	
}
