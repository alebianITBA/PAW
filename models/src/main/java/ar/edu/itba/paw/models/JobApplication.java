package ar.edu.itba.paw.models;

import java.util.Date;

public class JobApplication {
	private Long id;
	private String description;
	private Long userId;
	private Long jobOfferId;
	private Date createdAt;

	public JobApplication() {
		
	}
	
	public JobApplication(Long id, String description, Long userId, Long jobOfferId, Date createdAt) {
		this.id = id;
		this.description = description;
		this.userId = userId;
		this.jobOfferId = jobOfferId;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getJobOfferId() {
		return jobOfferId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setJobOfferId(Long jobOfferId) {
		this.jobOfferId = jobOfferId;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
