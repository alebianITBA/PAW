package ar.edu.itba.paw.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class PostgresqlUserDao extends PostgresqlAdapter implements UserDao {

	public User create(String email, String password) throws SQLException {
		String statement = "INSERT INTO users (email, password, created_at) VALUES ('" + email + "', '" + password +"', current_timestamp);";
		executeSql(statement);
		return getByEmail(email);
	}

	public void delete(Long id) {
	}

	public void update(String firstName, String lastName, String email, String password) {
	}

	public User getByEmail(String email) throws SQLException {
		String sqlStatement = "SELECT * FROM users WHERE email = '" + email + "';";
		Long id = null;
		String firstName = null;
		String lastName = null;
		String emailRetreived = null;
		String password = null;
		Date createdAt = null;
		
	    Connection connection = null;
	    Statement statement = null;
	    try {
	        Class.forName("org.postgresql.Driver");
	        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE_NAME, DATABASE_USER, DATABASE_PASSWORD);
	        statement = connection.createStatement();
	        ResultSet result = statement.executeQuery(sqlStatement);
	        
			while (result.next()) {
				id = result.getLong("id");
				firstName = result.getString("first_name");
				lastName = result.getString("last_name");
				emailRetreived = result.getString("email");
				password = result.getString("password");
				createdAt = result.getDate("created_at");
			}
	        
			result.close();
	        statement.close();
	        connection.close();
	    } catch ( Exception e ) {
	        System.err.println(e.getClass().getName()+": "+ e.getMessage());
	    }
		return new User(id, firstName, lastName, emailRetreived, password, createdAt);
	}

	public Long count() {
		return null;
	}

	public User find(Long id) {
		return null;
	}
	
	public void createTable() {
		String statement = "CREATE TABLE IF NOT EXISTS users (" +
						   "id serial PRIMARY KEY," +
			               "email varchar(255) NOT NULL UNIQUE," +
			               "first_name varchar(255)," +
			               "last_name varchar(255)," +
			               "password varchar(255) NOT NULL," +
			               "created_at timestamp NOT NULL" +
			               ");";
		executeSql(statement);
	}
}
