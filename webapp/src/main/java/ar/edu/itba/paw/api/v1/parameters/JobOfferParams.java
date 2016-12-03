package ar.edu.itba.paw.api.v1.parameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobOfferParams {
  @XmlElement
  public String title;
  @XmlElement
  public String description;
  @XmlElement
  public String skillIds;
}
