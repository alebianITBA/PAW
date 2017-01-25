package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.api.v1.ErrorCodes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorDTO {
  @XmlElement
  private int code;
  @XmlElement
  private String message;

  public ErrorDTO() {
  }

  public ErrorDTO(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  public ErrorDTO(final ErrorCodes error) {
    this.code = error.code;
    this.message = error.message;
  }
}
