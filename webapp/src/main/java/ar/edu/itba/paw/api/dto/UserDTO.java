package ar.edu.itba.paw.api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ar.edu.itba.paw.models.Skill;
import ar.edu.itba.paw.models.User;

@XmlRootElement()
public class UserDTO {
	
	@XmlElement(name="email")
	private String email;
	
	@XmlElement(name="id")
	private Long id;
	
	@XmlElement(name="last_name")
	private String lastName;
	
	@XmlElement(name="first_name")
	private String firstName;

	@XmlElement(name="avatar")
	private String avatar;

	@XmlElement(name="skills")
	private List<Skill> skills;
	
	public UserDTO() {}

	public UserDTO(User user) {
		this.email = user.getEmail();
		this.id = user.getId();
		this.lastName = user.getLastName();
		this.firstName = user.getFirstName();
		this.avatar = user.getGravatar();
		this.skills = user.getSkills();
	}

	public static List<UserDTO> fromList(List<User> users) {
		List<UserDTO> answer = new ArrayList<UserDTO>();
		for (User user : users) {
			answer.add(new UserDTO(user));
		}
		return answer;
	}

}
