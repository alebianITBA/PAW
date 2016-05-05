package ar.edu.itba.paw.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserSkillDao;
import ar.edu.itba.paw.models.Skill;

@Repository
public class UserSkillJDBCDao implements UserSkillDao {

	private final JdbcTemplate jdbcTemplate;
	private final SkillRowMapper skillRowMapper;

	@Autowired
	public UserSkillJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		skillRowMapper = new SkillRowMapper();
	}

	@Override
	public void create(Long userId, Long skillId) {
		jdbcTemplate.update("INSERT INTO user_skills (user_id, skill_id, created_at) VALUES (?, ?, current_timestamp);",
				userId, skillId);
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM user_skills WHERE id = ?", id);
	}

	@Override
	public void removeUserSkill(Long userId, Long skillId) {
		jdbcTemplate.update("DELETE FROM user_skills WHERE user_id = ? AND skill_id = ?", userId, skillId);
	}

	@Override
	public List<Skill> userSkills(Long userId) {
		return jdbcTemplate.query(
				"SELECT skills.id as id, skills.name as name, skills.created_at as created_at FROM "
						+ "user_skills INNER JOIN skills ON (skills.id = user_skills.skill_id) "
						+ "INNER JOIN users ON (users.id = user_skills.user_id) WHERE users.id = ?;",
				skillRowMapper, userId);
	}

	private static class SkillRowMapper implements RowMapper<Skill> {
		public Skill mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new Skill(rs.getLong("id"), rs.getString("name"), rs.getDate("created_at"));
		}
	}
}
