package ar.edu.itba.paw.models;

import java.util.Date;

public class JobApplication {
	private Long id;
	private String description;
	private Long userId;
	private Long jobOfferId;
	private Date createdAt;
	private User user;

	public JobApplication(Long id, String description, Long userId, Long jobOfferId, Date createdAt) {
		this.id = id;
		this.description = description;
		this.userId = userId;
		this.jobOfferId = jobOfferId;
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (!(other instanceof JobApplication)) {
			return false;
		}

		JobApplication otherApplication = (JobApplication) other;

		if (!(id == otherApplication.id)) {
			return false;
		}

		if (!(description == otherApplication.description)) {
			return false;
		}

		if (!(userId == otherApplication.userId)) {
			return false;
		}

		if (!(jobOfferId == otherApplication.jobOfferId)) {
			return false;
		}

		return true;
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

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

}
