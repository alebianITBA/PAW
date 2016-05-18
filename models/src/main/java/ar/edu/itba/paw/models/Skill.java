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

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (!(other instanceof Skill)) {
			return false;
		}

		Skill otherSkill = (Skill) other;

		if (!(id == otherSkill.id)) {
			return false;
		}

		if (!(name == otherSkill.name)) {
			return false;
		}

		return true;
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
