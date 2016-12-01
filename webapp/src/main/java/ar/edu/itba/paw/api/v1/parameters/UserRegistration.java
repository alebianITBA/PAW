package ar.edu.itba.paw.api.v1.parameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRegistration {
  @XmlElement
  public String email;
  @XmlElement
  public String password;
  @XmlElement
  public String passwordConfirmation;
  @XmlElement
  public String firstName;
  @XmlElement
  public String lastName;
}
