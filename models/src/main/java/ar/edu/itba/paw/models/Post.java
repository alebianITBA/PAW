package ar.edu.itba.paw.models;

import java.util.Date;

public class Post {
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private Date createdAt;
	private User user;

	public Post(Long id, String title, String description, Long userId, Date createdAt) {
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
		if (!(other instanceof Post)) {
			return false;
		}

		Post otherSkill = (Post) other;

		if (!(id == otherSkill.id)) {
			return false;
		}

		if (!(title == otherSkill.title)) {
			return false;
		}

		if (!(description == otherSkill.description)) {
			return false;
		}

		if (!(userId == otherSkill.userId)) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
