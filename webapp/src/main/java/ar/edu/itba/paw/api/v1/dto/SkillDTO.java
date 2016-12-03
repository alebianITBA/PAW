package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class SkillDTO {
  @XmlElement
  private Long id;
  @XmlElement
  private String name;

  public SkillDTO() {
  }

  public SkillDTO(final Skill skill) {
    this.id = skill.getId();
    this.name = skill.getName();
  }

  public static List<SkillDTO> fromList(List<Skill> skills) {
    return skills.stream().map(s -> new SkillDTO(s)).collect(Collectors.toList());
  }
}
