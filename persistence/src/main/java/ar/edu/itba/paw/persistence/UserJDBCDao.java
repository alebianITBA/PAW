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

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class UserJDBCDao implements UserDao {

	private final JdbcTemplate jdbcTemplate;
	private final UserRowMapper userRowMapper;

	@Autowired
	public UserJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");

		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY,"
				+ "email varchar(255) NOT NULL UNIQUE, first_name varchar(255), last_name varchar(255),"
				+ "password varchar(255) NOT NULL, created_at timestamp NOT NULL);");
		userRowMapper = new UserRowMapper();
	}

	public void create(String email, String password) {
		jdbcTemplate.update("INSERT INTO users (email, password, created_at) VALUES (?, ?, current_timestamp);", email,
				password);
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
	}

	public void update(Long id, String firstName, String lastName, String email, String password) {
		jdbcTemplate.update(
				"UPDATE users SET first_name = COALESCE(?, first_name), last_name = COALESCE(?, last_name), "
						+ "email = COALESCE(?, email), password = COALESCE(?, password) WHERE id = ?;",
				firstName, lastName, email, password, id);
	}

	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?;", userRowMapper, email);
	}

	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users;", Long.class);
	}

	public User find(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?;", userRowMapper, id);
	}
	
	public List<User> all() {
		return jdbcTemplate.query("SELECT * FROM users;", userRowMapper);
	}

	private static class UserRowMapper implements RowMapper<User> {
		public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("email"), rs.getString("password"), rs.getDate("created_at"));
		}
	}
}
