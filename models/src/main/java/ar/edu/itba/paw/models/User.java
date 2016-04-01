package ar.edu.itba.paw.models;

import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date createdAt;
	private static User nullUser;
	
	public User(Long id, String firstName, String lastName, String email, String password, Date createdAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdAt = new Date(createdAt.getTime());
	}
	
	public static User nullUser() {
		if (nullUser == null) {
		 nullUser = new User(0L, "Null", "User", "null@null.com", "nullpassword", new Date());
		}
		return nullUser;
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
