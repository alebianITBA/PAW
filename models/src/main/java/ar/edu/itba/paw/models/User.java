package ar.edu.itba.paw.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ar.edu.itba.paw.utils.MD5Util;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
	@SequenceGenerator(sequenceName = "users_id_seq", name = "users_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", length = 255)
	private String firstName;

	@Column(name = "last_name", length = 255)
	private String lastName;

	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Column(name = "created_at")
	private Date createdAt;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinTable(name = "user_skills", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "skill_id", referencedColumnName = "id") })
	@OrderBy("name ASC")
	private List<Skill> skills;

	protected User() {
		/* Just for Hibernate */
	}

	public User(Long id, String firstName, String lastName, String email, String password, Date createdAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = new Date(createdAt.getTime());
	}

	public User(Long id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(String firstName, String lastName, String email, String password, Date createdAt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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
		if (!(other instanceof User)) {
			return false;
		}

		User otherUser = (User) other;

		if (!(id == otherUser.id)) {
			return false;
		}

		if (!(firstName == otherUser.firstName)) {
			return false;
		}

		if (!(lastName == otherUser.lastName)) {
			return false;
		}

		if (!(email == otherUser.email)) {
			return false;
		}

		if (!(password == otherUser.password)) {
			return false;
		}

		return true;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Date getCreatedAt() {
		return new Date(createdAt.getTime());
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getGravatar() {
		return "http://www.gravatar.com/avatar/" + MD5Util.INSTANCE.md5Hex(getEmail());
	}

	@Override
	public String toString() {
		return "[ " + firstName + " " + lastName + " (email: " + email + ", id: " + id.toString() + ") ]";
	}

}
