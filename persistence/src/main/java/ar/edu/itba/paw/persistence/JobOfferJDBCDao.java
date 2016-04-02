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

import ar.edu.itba.paw.interfaces.JobOfferDao;
import ar.edu.itba.paw.models.JobOffer;

@Repository
public class JobOfferJDBCDao implements JobOfferDao {

	private final JdbcTemplate jdbcTemplate;
	private final JobOfferRowMapper jobOfferRowMapper;

	@Autowired
	public JobOfferJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		jobOfferRowMapper = new JobOfferRowMapper();
	}

	public void create(String title, String description, Long userId) {
		jdbcTemplate.update("INSERT INTO job_offers (title, description, user_id, created_at) VALUES"
				+ "(?, ?, ?, current_timestamp);", title, description, userId);
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM job_offers WHERE id = ?", id);
	}

	public void update(Long id, String title, String description) {
		jdbcTemplate.update(
				"UPDATE job_offers SET title = COALESCE(?, title), description = COALESCE(?, description), WHERE id = ?;",
				title, description, id);
	}

	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM job_offers;", Long.class);
	}

	public JobOffer find(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM job_offers WHERE id = ?;", jobOfferRowMapper, id);
	}

	public List<JobOffer> all() {
		return jdbcTemplate.query("SELECT * FROM job_offers;", jobOfferRowMapper);
	}

	public List<JobOffer> userJobOffers(Long userId) {
		return jdbcTemplate.query("SELECT * FROM job_offers WHERE user_id = ?;", jobOfferRowMapper, userId);
	}

	private static class JobOfferRowMapper implements RowMapper<JobOffer> {
		public JobOffer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new JobOffer(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
					rs.getLong("user_id"), rs.getDate("created_at"));
		}
	}
}
