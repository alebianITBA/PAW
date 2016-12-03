package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class UserDTO {
  @XmlElement
  private String email;

  @XmlElement
  private Long id;

  @XmlElement
  private String last_name;

  @XmlElement
  private String first_name;

  @XmlElement
  private String avatar;

  @XmlElement
  private List<SkillDTO> skills;

  public UserDTO() {
  }

  public UserDTO(User user) {
    this.email = user.getEmail();
    this.id = user.getId();
    this.last_name = user.getLastName();
    this.first_name = user.getFirstName();
    this.avatar = user.getGravatar();
    this.skills = SkillDTO.fromList(user.getSkills());
  }

  public static List<UserDTO> fromList(List<User> users) {
    return users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
  }

}
