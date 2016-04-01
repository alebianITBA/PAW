package ar.edu.itba.paw.models;

import java.util.Date;

public class JobOffer {
	private Long id;
	private String title;
	private String description;
	private int userId;
	private Date createdAt;

	public JobOffer(Long id, String title, String description, int userId, Date createdAt) {
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

	public int getUserId() {
		return userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}