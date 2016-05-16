package ar.edu.itba.paw.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class RegisterForm {

  @NotBlank
  @Length(max = 255)
  @Pattern(regexp = "[a-zA-Z]+")
  private String firstName;

  @NotBlank
  @Length(max = 255)
  @Pattern(regexp = "[a-zA-Z]+")
  private String lastName;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Length(min = 6, max = 20)
  private String password;

  private String confirmPassword;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

}
