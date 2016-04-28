package ar.edu.itba.paw.models;

import java.util.Date;
import java.util.List;

public class JobOffer {
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private Date createdAt;
	private List<Skill> skills;

	public JobOffer() {
		
	}
	
	public JobOffer(Long id, String title, String description, Long userId, Date createdAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public List<Skill> getSkills() {
		return this.skills;
	}

}
