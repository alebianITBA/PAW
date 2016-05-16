package ar.edu.itba.paw.models;

import ar.edu.itba.paw.utils.MD5Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class User {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Date createdAt;
  private List<Skill> skills;
  private static User nullUser;

  public User() {
  }

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

  @Override
  public boolean equals(Object other) {
    User otherUser = (User) other;
    return (this.id.equals(otherUser.getId())) && (this.firstName.equals(otherUser.getFirstName()))
        && (this.lastName.equals(otherUser.getLastName())) && (this.email.equals(otherUser.getEmail()));
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

  public static User getNullUser() {
    return nullUser;
  }

  public static void setNullUser(User nullUser) {
    User.nullUser = nullUser;
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
    return "http://www.gravatar.com/avatar/" + MD5Util.md5Hex(getEmail());
  }

  @Override
  public String toString() {
    return firstName + " " + lastName + " (email: " + email + ", id: " + id.toString() + ")";
  }

}
