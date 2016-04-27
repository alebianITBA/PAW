package ar.edu.itba.paw.forms;

import java.util.ArrayList;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import ar.edu.itba.paw.models.Skill;

public class JobOfferForm {

	@NotBlank
	@Length(max = 255)
	private String title;
	
	@NotBlank
	private String description;
	
	private String selectedSkillIds;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSelectedSkillIds() {
		return selectedSkillIds;
	}
	public void setSelectedSkillIds(String selectedSkillIds) {
		this.selectedSkillIds = selectedSkillIds;
	}
	
	
	
}
