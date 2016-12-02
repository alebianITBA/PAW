package ar.edu.itba.paw.api.v1.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorDTO {
  @XmlElement
  private String errors;

  public ErrorDTO() {
  }

  public ErrorDTO(final String msg) {
    this.errors = msg;
  }
}
