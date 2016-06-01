package ar.edu.itba.paw.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "job_offers")
public class JobOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_offers_id_seq")
	@SequenceGenerator(sequenceName = "job_offers_id_seq", name = "job_offers_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "description", length = 2048)
	private String description;

	@Column(name = "created_at")
	private Date createdAt;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinTable(name = "job_offer_skills", joinColumns = {
			@JoinColumn(name = "job_offer_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "skill_id", referencedColumnName = "id") })
	@OrderBy("name ASC")
	@JoinColumn()
	private List<Skill> skills;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinTable(name = "job_applications", joinColumns = {
			@JoinColumn(name = "job_offer_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id", referencedColumnName = "id") })
	@OrderBy("created_at DESC")
	@JoinColumn()
	private List<JobApplication> applications;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "closed_at")
	private Date closedAt;

	protected JobOffer() {
		/* Just for Hibernate */
	}

	public JobOffer(Long id, String title, String description, User user, Date createdAt, Date closedAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
		this.createdAt = createdAt;
		this.closedAt = closedAt;
	}

	public JobOffer(String title, String description, User user, List<Skill> skills, Date createdAt) {
		this.title = title;
		this.description = description;
		this.user = user;
		this.skills = skills;
		this.createdAt = createdAt;
	}

	public JobOffer(String title, String description, User user, Date createdAt) {
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

		if (user != null) {
			if (!(user.equals(otherOffer.user))) {
				return false;
			}
		} else {
			if (otherOffer.user != null) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return "[ JOB OFFER: " + title + " OF USER: " + user.getId() + " ]";
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

	public Date getClosedAt() {
		return closedAt;
	}
	
	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
	
	public boolean containsAll(List<Skill> otherSkills) {
		if (skills == null) {
			return (otherSkills == null);
		} else {
			if (otherSkills == null) {
				return false;
			} else {
				return skills.containsAll(otherSkills);
			}
		}
	}

}
