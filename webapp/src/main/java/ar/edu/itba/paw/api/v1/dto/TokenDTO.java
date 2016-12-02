package ar.edu.itba.paw.api.v1.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TokenDTO {
  @XmlElement
  private String token;

  public TokenDTO() {
  }

  public TokenDTO(final String token) {
    this.token = token;
  }
}
