package ar.edu.itba.paw.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.interfaces.SkillService;
import ar.edu.itba.paw.models.JobOffer;
import ar.edu.itba.paw.models.Skill;

@Repository
public class JobOfferJDBCDao implements JobOfferDao {

	private final JdbcTemplate jdbcTemplate;
	private final JobOfferRowMapper jobOfferRowMapper;

	@Autowired
	private SkillService skillService;

	@Autowired
	public JobOfferJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		jobOfferRowMapper = new JobOfferRowMapper();
	}

	@Override
	public Long create(final String title, final String description, final Long userId) {

		// Cambio esto para probar de tener el ID generado
		final String sql = "INSERT INTO job_offers (title, description, user_id, created_at) VALUES"
				+ "(?, ?, ?, current_timestamp);";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(sql, new String[] { "id" });
				pst.setString(1, title);
				pst.setString(2, description);
				pst.setLong(3, userId);
				return pst;
			}
		}, keyHolder);
		return new Long(keyHolder.getKey().toString());
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM job_offers WHERE id = ?", id);
	}

	@Override
	public void update(Long id, String title, String description) {
		jdbcTemplate.update(
				"UPDATE job_offers SET title = COALESCE(?, title), description = COALESCE(?, description) WHERE id = ?;",
				title, description, id);
	}

	@Override
	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM job_offers;", Long.class);
	}

	@Override
	public JobOffer find(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM job_offers WHERE id = ?;", jobOfferRowMapper, id);
	}

	@Override
	public List<JobOffer> all() {
		return jdbcTemplate.query("SELECT * FROM job_offers;", jobOfferRowMapper);
	}

	@Override
	public List<JobOffer> all(Integer page, Integer perPage) {
		return jdbcTemplate.query("SELECT * FROM job_offers ORDER BY created_at DESC LIMIT ? OFFSET ?;",
				jobOfferRowMapper, perPage, page - 1);
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId) {
		return jdbcTemplate.query("SELECT * FROM job_offers WHERE user_id = ?;", jobOfferRowMapper, userId);
	}

	@Override
	public List<JobOffer> userJobOffers(Long userId, Integer page, Integer perPage) {
		return jdbcTemplate.query(
				"SELECT * FROM job_offers WHERE user_id = ? ORDER BY created_at DESC LIMIT ? OFFSET ?;",
				jobOfferRowMapper, userId, perPage, page - 1);
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills) {
		return jdbcTemplate
				.query("SELECT DISTINCT job_offers.* FROM job_offers INNER JOIN job_offer_skills ON job_offers.id = job_offer_skills.job_offer_id "
						+ "WHERE job_offer_skills.skill_id IN (" + skillsToString(skills)
						+ ") ORDER BY job_offers.created_at DESC;", jobOfferRowMapper);
	}

	@Override
	public List<JobOffer> withSkills(List<Skill> skills, Integer page, Integer perPage) {
		return jdbcTemplate.query(
				"SELECT DISTINCT job_offers.* FROM job_offers INNER JOIN job_offer_skills ON job_offers.id = job_offer_skills.job_offer_id "
						+ "WHERE job_offer_skills.skill_id IN (" + skillsToString(skills)
						+ ") ORDER BY job_offers.created_at DESC LIMIT ? OFFSET ?;",
				jobOfferRowMapper, perPage, page - 1);
	}

	private static class JobOfferRowMapper implements RowMapper<JobOffer> {
		public JobOffer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new JobOffer(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
					rs.getLong("user_id"), rs.getDate("created_at"));
		}
	}

	// Hack TODO: find a better way to do it
	private String skillsToString(List<Skill> skills) {
		List<Skill> listToUse;
		if (skills.size() > 0) {
			listToUse = skills;
		} else {
			listToUse = skillService.all();
		}
		StringBuilder answer = new StringBuilder();
		for (Skill skill : listToUse) {
			answer.append(skill.getId().toString());
			answer.append(",");
		}
		answer.setLength(answer.length() - 1);
		return answer.toString();
	}

}
