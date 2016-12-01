package ar.edu.itba.paw.api.v1.dto;

import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement()
public class UserDTO {

  @XmlElement(name = "email")
  private String email;

  @XmlElement(name = "id")
  private Long id;

  @XmlElement(name = "last_name")
  private String lastName;

  @XmlElement(name = "first_name")
  private String firstName;

  @XmlElement(name = "avatar")
  private String avatar;

  @XmlElement(name = "skills")
  private List<String> skills;

  public UserDTO() {
  }

  public UserDTO(User user) {
    this.email = user.getEmail();
    this.id = user.getId();
    this.lastName = user.getLastName();
    this.firstName = user.getFirstName();
    this.avatar = user.getGravatar();
    this.skills = user.getSkills().stream().map(s -> s.getName()).collect(Collectors.toList());
  }

  public static List<UserDTO> fromList(List<User> users) {
    return users.stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
  }

}
