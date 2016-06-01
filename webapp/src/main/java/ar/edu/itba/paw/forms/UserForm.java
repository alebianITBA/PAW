package ar.edu.itba.paw.forms;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class UserForm {

	private Long id;
	
	@NotBlank
	@Length(max = 255)
	@Pattern(regexp = "[a-zA-Z ]+")
	private String firstName;

	@NotBlank
	@Length(max = 255)
	@Pattern(regexp = "[a-zA-Z ]+")
	private String lastName;

	private String selectedSkillIds;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSelectedSkillIds() {
		return selectedSkillIds;
	}

	public void setSelectedSkillIds(String selectedSkillIds) {
		this.selectedSkillIds = selectedSkillIds;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
