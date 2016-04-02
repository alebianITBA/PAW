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

import ar.edu.itba.paw.interfaces.JobApplicationDao;
import ar.edu.itba.paw.models.JobApplication;

@Repository
public class JobApplicationJDBCDao implements JobApplicationDao {

	private final JdbcTemplate jdbcTemplate;
	private final JobApplicationRowMapper jobApplicationRowMapper;

	@Autowired
	public JobApplicationJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		jobApplicationRowMapper = new JobApplicationRowMapper();
	}

	public void create(String description, Long userId, Long jobOfferId) {
		jdbcTemplate.update("INSERT INTO job_applications (description, user_id, job_offer_id, created_at) VALUES"
				+ "(?, ?, ?, current_timestamp);", description, userId, jobOfferId);
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM job_applications WHERE id = ?", id);
	}

	public void update(Long id, String description) {
		jdbcTemplate.update("UPDATE job_applications SET description = COALESCE(?, title), WHERE id = ?;", description,
				id);
	}

	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM job_applications;", Long.class);
	}

	public JobApplication find(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM job_applications WHERE id = ?;", jobApplicationRowMapper, id);
	}

	public List<JobApplication> all() {
		return jdbcTemplate.query("SELECT * FROM job_applications;", jobApplicationRowMapper);
	}

	public List<JobApplication> userJobApplications(Long userId) {
		return jdbcTemplate.query("SELECT * FROM job_applications WHERE user_id = ?;", jobApplicationRowMapper, userId);
	}

	public List<JobApplication> jobOfferApplications(Long jobOfferId) {
		return jdbcTemplate.query("SELECT * FROM job_applications WHERE job_offer_id = ?;", jobApplicationRowMapper, jobOfferId);
	}

	private static class JobApplicationRowMapper implements RowMapper<JobApplication> {
		public JobApplication mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new JobApplication(rs.getLong("id"), rs.getString("description"), rs.getLong("user_id"),
					rs.getLong("job_offer_id"), rs.getDate("created_at"));
		}
	}
}
