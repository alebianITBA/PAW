package ar.edu.itba.paw.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

public class SkillTest {
	
	private final Long ID = 1L;
	private final String NAME = "TEST";
	
	@Test
    public void validSkillTest() {
        Skill skill = new Skill(ID, NAME, new Date());
        
        assertEquals(ID, skill.getId());
        assertEquals(NAME, skill.getName());
    }
	
	@Test
    public void skillToString() {
		Skill skill = new Skill(ID, NAME, new Date());
        
        assertEquals("[ SKILL: TEST ]", skill.toString());
    }
	
	@Test
    public void skillHashCode() {
		Skill skill = new Skill(ID, NAME, new Date());
        
        assertEquals(1, skill.hashCode());
    }
	
	@Test
	public void skillEquals() {
		Skill skill = new Skill(ID, NAME, new Date());
		Skill identicalSkill = new Skill(ID, NAME, new Date());
		Skill otherSkill = new Skill(ID, "TEST2", new Date());
		Skill noNameSkill = new Skill(ID, null, new Date());
		Skill noIDSkill = new Skill(null, NAME, new Date());
		Skill noDataSkill = new Skill(null, null, null);
		
		assertEquals(skill, skill);
		assertEquals(skill, identicalSkill);
		assertNotEquals(skill, otherSkill);
		assertNotEquals(skill, noNameSkill);
		assertNotEquals(skill, noIDSkill);
		assertNotEquals(skill, noDataSkill);
	}
}
