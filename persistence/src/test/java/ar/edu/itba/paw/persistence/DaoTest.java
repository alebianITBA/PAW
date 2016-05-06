package ar.edu.itba.paw.persistence;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;

@ContextConfiguration(classes = TestConfig.class)
public class DaoTest {
	
	@Autowired
	protected DataSource ds;
	
	protected JdbcTemplate jdbcTemplate;

	public void cleanUp() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "user_skills");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "job_offer_skills");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "job_applications");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "posts");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "skills");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "job_offers");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
	}
}
