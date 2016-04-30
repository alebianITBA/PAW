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

import ar.edu.itba.paw.interfaces.PostDao;
import ar.edu.itba.paw.models.Post;

@Repository
public class PostJDBCDao implements PostDao {

	private final JdbcTemplate jdbcTemplate;
	private final PostRowMapper postRowMapper;

	@Autowired
	public PostJDBCDao(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
		new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		postRowMapper = new PostRowMapper();
	}

	public void create(String title, String description, Long userId) {
		jdbcTemplate.update("INSERT INTO posts (title, description, user_id, created_at) VALUES"
				+ "(?, ?, ?, current_timestamp);", title, description, userId);
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM posts WHERE id = ?", id);
	}

	public void update(Long id, String title, String description) {
		jdbcTemplate.update(
				"UPDATE posts SET title = COALESCE(?, title), description = COALESCE(?, description), WHERE id = ?;",
				title, description, id);
	}

	public Long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM posts;", Long.class);
	}

	public Post find(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM posts WHERE id = ?;", postRowMapper, id);
	}

	public List<Post> all() {
		return jdbcTemplate.query("SELECT * FROM posts;", postRowMapper);
	}

	public List<Post> userPosts(Long userId) {
		return jdbcTemplate.query("SELECT * FROM posts WHERE user_id = ?;", postRowMapper, userId);
	}

	private static class PostRowMapper implements RowMapper<Post> {
		public Post mapRow(final ResultSet rs, final int rowNum) throws SQLException {
			return new Post(rs.getLong("id"), rs.getString("title"), rs.getString("description"), rs.getLong("user_id"),
					rs.getDate("created_at"));
		}
	}
}