package ar.edu.itba.paw.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_seq")
	@SequenceGenerator(sequenceName = "posts_id_seq", name = "posts_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "description", length = 2048)
	private String description;

	@Column(name = "created_at")
	private Date createdAt;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	protected Post() {
		/* Just for Hibernate */
	}

	public Post(Long id, String title, String description, User user, Date createdAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
		this.createdAt = createdAt;
	}

	public Post(String title, String description, User user, Date createdAt) {
		this.title = title;
		this.description = description;
		this.user = user;
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
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

		Post otherPost = (Post) other;

		if (!(id == otherPost.id)) {
			return false;
		}

		if (!(title == otherPost.title)) {
			return false;
		}

		if (!(description == otherPost.description)) {
			return false;
		}

		if (user != null) {
			if (!(user.equals(otherPost.user))) {
				return false;
			}
		} else {
			if (otherPost.user != null) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return "[ POST: " + title + " OF USER: " + user.getId() + " ]";
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

	public User getUser() {
		return user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
