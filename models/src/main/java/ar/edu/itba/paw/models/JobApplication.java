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
@Table(name = "job_applications")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_applications_id_seq")
	@SequenceGenerator(sequenceName = "job_applications_id_seq", name = "job_applications_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "description", length = 2048)
	private String description;

	@Column(name = "created_at")
	private Date createdAt;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_offer_id")
	private JobOffer jobOffer;

	protected JobApplication() {
		/* Just for Hibernate */
	}

	public JobApplication(Long id, String description, User user, JobOffer jobOffer, Date createdAt) {
		this.id = id;
		this.description = description;
		this.user = user;
		this.jobOffer = jobOffer;
		this.createdAt = createdAt;
	}

	public JobApplication(String description, User user, JobOffer jobOffer, Date createdAt) {
		this.description = description;
		this.user = user;
		this.jobOffer = jobOffer;
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

		if (user != null) {
			if (!(user.equals(otherApplication.user))) {
				return false;
			}
		} else {
			if (otherApplication.user != null) {
				return false;
			}
		}

		if (jobOffer != null) {
			if (!(jobOffer.equals(otherApplication.jobOffer))) {
				return false;
			}
		} else {
			if (otherApplication.jobOffer != null) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return "[ JOB APPLICATION OF USER: " + user.getId() + " TO JOB OFFER: " + jobOffer.getId() + " ]";
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public User getUser() {
		return user;
	}

	public JobOffer getJobOffer() {
		return jobOffer;
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

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
