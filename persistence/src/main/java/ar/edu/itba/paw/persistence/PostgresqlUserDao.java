package ar.edu.itba.paw.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class PostgresqlUserDao implements UserDao {

	public User create(String email, String password) throws SQLException {
		String statement = "INSERT INTO users (email, password, created_at) VALUES ('" + email + "', '" + password
				+ "', current_timestamp);";
		PostgresqlAdapter.executeInsertDeleteOrUpdateSql(statement);
		return findByEmail(email);
	}

	public void delete(Long id) {
		String statement = "DELETE FROM users WHERE id = " + id.toString() + ";";
		PostgresqlAdapter.executeInsertDeleteOrUpdateSql(statement);
		return;
	}

	public void update(String firstName, String lastName, String email, String password) {
	}

	public User findByEmail(String email) throws SQLException {
		String sqlStatement = "SELECT * FROM users WHERE email = '" + email + "';";

		Connection connection = PostgresqlAdapter.getNewConnection();
		if (connection == null) {
			return User.nullUser();
		}

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sqlStatement);
		
		if (!result.next()) {
			return User.nullUser();
		}

		Long id = result.getLong("id");
		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		String password = result.getString("password");
		Date createdAt = result.getDate("created_at");

		result.close();
		statement.close();
		connection.close();
		return new User(id, firstName, lastName, email, password, createdAt);
	}

	public Long count() {
		return null;
	}

	public User find(Long id) throws SQLException {
		String sqlStatement = "SELECT * FROM users WHERE id = '" + id.toString() + "';";

		Connection connection = PostgresqlAdapter.getNewConnection();
		if (connection == null) {
			return User.nullUser();
		}

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sqlStatement);
		
		if (!result.next()) {
			return User.nullUser();
		}

		String firstName = result.getString("first_name");
		String lastName = result.getString("last_name");
		String emailRetreived = result.getString("email");
		String password = result.getString("password");
		Date createdAt = result.getDate("created_at");

		result.close();
		statement.close();
		connection.close();
		return new User(id, firstName, lastName, emailRetreived, password, createdAt);
	}

	public void createTable() {
		String statement = "CREATE TABLE IF NOT EXISTS users (" + "id serial PRIMARY KEY,"
				+ "email varchar(255) NOT NULL UNIQUE," + "first_name varchar(255)," + "last_name varchar(255),"
				+ "password varchar(255) NOT NULL," + "created_at timestamp NOT NULL" + ");";
		PostgresqlAdapter.executeInsertDeleteOrUpdateSql(statement);
	}
}
