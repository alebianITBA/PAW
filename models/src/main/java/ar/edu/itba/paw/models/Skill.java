package ar.edu.itba.paw.models;

import java.util.Date;

public class Skill {
	private Long id;
	private String name;
	private Date createdAt;

	public Skill(Long id, String name, Date createdAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}
