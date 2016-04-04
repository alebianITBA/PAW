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

import ar.edu.itba.paw.interfaces.SkillDao;
import ar.edu.itba.paw.models.Skill;

@Repository
public class SkillJDBCDao implements SkillDao {

	private final JdbcTemplate jdbcTemplate;
	private final SkillRowMapper skillRowMapper;

	@Autowired
	public SkillJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		skillRowMapper = new SkillRowMapper();
	}

	public void create(String name) {
		jdbcTemplate.update("INSERT INTO skills (name, created_at) VALUES (?, current_timestamp);", name);
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM skills WHERE id = ?", id);
	}

	public void update(Long id, String name) {
		jdbcTemplate.update("UPDATE skills SET name = COALESCE(?, name) WHERE id = ?;", name, id);
	}

	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM skills;", Long.class);
	}

	public Skill find(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM skills WHERE id = ?;", skillRowMapper, id);
	}

	public Skill findByName(String name) {
		return jdbcTemplate.queryForObject("SELECT * FROM skills WHERE name = ?;", skillRowMapper, name);
	}

	public List<Skill> all() {
		return jdbcTemplate.query("SELECT * FROM skills;", skillRowMapper);
	}

	private static class SkillRowMapper implements RowMapper<Skill> {
		public Skill mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new Skill(rs.getLong("id"), rs.getString("name"), rs.getDate("created_at"));
		}
	}

}
