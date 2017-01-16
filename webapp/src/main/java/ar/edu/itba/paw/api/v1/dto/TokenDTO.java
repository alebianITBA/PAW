package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.utils.Token;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TokenDTO {
  @XmlElement
  private String token;
  @XmlElement
  private UserDTO user;

  public TokenDTO() {
  }

  public TokenDTO(final String token) {
    this.token = token;
  }

  public TokenDTO(final User user) {
    this.token = Token.create(user);
    this.user = new UserDTO(user);
  }
}
