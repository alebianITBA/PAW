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
	private User user;

	public JobOffer() {

	}

	public JobOffer(Long id, String title, String description, Long userId, Date createdAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
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
		if (!(other instanceof JobOffer)) {
			return false;
		}

		JobOffer otherOffer = (JobOffer) other;

		if (!(id == otherOffer.id)) {
			return false;
		}

		if (!(title == otherOffer.title)) {
			return false;
		}

		if (!(description == otherOffer.description)) {
			return false;
		}

		if (!(userId == otherOffer.userId)) {
			return false;
		}

		return true;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
