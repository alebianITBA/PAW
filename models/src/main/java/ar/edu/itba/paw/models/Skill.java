package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skills_id_seq")
  @SequenceGenerator(sequenceName = "skills_id_seq", name = "skills_id_seq", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", length = 255)
  private String name;

  @Column(name = "created_at")
  private Date createdAt;

  protected Skill(){
    /* Just for Hibernate */
  }

  public static Skill nullSkill() {
    return new Skill(0L, "NULL", new Date());
  }

  public static List<Skill> nullList() {
    List<Skill> skills = new LinkedList<>();
    skills.add(nullSkill());
    return skills;
  }

  public Skill(Long id, String name, Date createdAt) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
  }

  public Skill(String name, Date createdAt) {
    this.name = name;
    this.createdAt = createdAt;
  }

  @Override
    public int hashCode() {
        return id.hashCode();
    }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other == this) {
      return true;
    }
    if (!(other instanceof Skill)) {
      return false;
    }

    Skill otherSkill = (Skill) other;

    if (!(id == otherSkill.id)) {
      return false;
    }

    if (!(name == otherSkill.name)) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "[ SKILL: " + name + " ]";
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

}
