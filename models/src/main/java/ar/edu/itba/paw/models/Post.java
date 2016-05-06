package ar.edu.itba.paw.models;

import java.util.Date;

public class Post {
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private Date createdAt;

	public Post(Long id, String title, String description, Long userId, Date createdAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object other) {
		Post otherOffer = (Post) other;
		return (this.id.equals(otherOffer.getId())) && (this.title.equals(otherOffer.getTitle()))
				&& (this.description.equals(otherOffer.getDescription()))
				&& (this.userId.equals(otherOffer.getUserId()));
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

}
