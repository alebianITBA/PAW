package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.JobOfferSkillDao;
import ar.edu.itba.paw.models.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class JobOfferSkillJDBCDao implements JobOfferSkillDao {

  private final JdbcTemplate jdbcTemplate;
  private final SkillRowMapper skillRowMapper;

  @Autowired
  public JobOfferSkillJDBCDao(final DataSource ds) {
    jdbcTemplate = new JdbcTemplate(ds);
    new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
    skillRowMapper = new SkillRowMapper();
  }

  @Override
  public void create(Long jobOfferId, Long skillId) {
    jdbcTemplate.update(
        "INSERT INTO job_offer_skills (job_offer_id, skill_id, created_at) VALUES (?, ?, current_timestamp);",
        jobOfferId, skillId);
  }

  @Override
  public void delete(Long id) {
    jdbcTemplate.update("DELETE FROM job_offer_skills WHERE id = ?", id);
  }

  @Override
  public void removeJobOfferSkills(Long jobOfferId) {
    jdbcTemplate.update("DELETE FROM job_offer_skills WHERE job_offer_id = ?;", jobOfferId);
  }

  @Override
  public void removeJobOfferSkill(Long jobOfferId, Long skillId) {
    jdbcTemplate.update("DELETE FROM job_offer_skills WHERE job_offer_id = ? AND skill_id = ?", jobOfferId,
        skillId);
  }

  @Override
  public List<Skill> jobOfferSkills(Long jobOfferId) {
    return jdbcTemplate.query(
        "SELECT skills.id as id, skills.name as name, skills.created_at as created_at FROM "
            + "job_offer_skills INNER JOIN skills ON (skills.id = job_offer_skills.skill_id) "
            + "INNER JOIN job_offers ON (job_offers.id = job_offer_skills.job_offer_id) WHERE job_offers.id = ?;",
        skillRowMapper, jobOfferId);
  }

  private static class SkillRowMapper implements RowMapper<Skill> {
    public Skill mapRow(final ResultSet rs, final int rowNum) throws SQLException {
      return new Skill(rs.getLong("id"), rs.getString("name"), rs.getDate("created_at"));
    }
  }
}
