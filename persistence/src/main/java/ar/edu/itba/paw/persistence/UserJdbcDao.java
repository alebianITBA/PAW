package ar.edu.itba.paw.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class UserJdbcDao implements UserDao {

	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;
	private final UserRowMapper userRowMapper;

	@Autowired
	public UserJdbcDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users");

		jdbcTemplate.execute(
				"CREATE TABLE IF NOT EXISTS users (" + "username varchar(100)," + "password varchar(100)" + ")");
		userRowMapper = new UserRowMapper();
	}

	public User create(final String username, final String password) {
		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", username);
		args.put("password", password);
		jdbcInsert.execute(args);

		return new User(username, password);
	}

	public User getByUsername(final String username) {
		final List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE username = ? LIMIT 1", userRowMapper,
				username);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	private static class UserRowMapper implements RowMapper<User> {

		public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new User(rs.getString("username"), rs.getString("password"));
		}
	}
}
