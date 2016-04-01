package ar.edu.itba.paw.models;

import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date createdAt;
	
	public User(Long id, String firstName, String lastName, String email, String password, Date createdAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = new Date(createdAt.getTime());
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
	
	@Override
	public String toString(){
		return firstName + " " + lastName + " (email: " + email + ", id: " + id.toString() + ")";
	}
}
