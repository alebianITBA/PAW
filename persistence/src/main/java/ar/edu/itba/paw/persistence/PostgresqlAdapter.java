package ar.edu.itba.paw.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresqlAdapter {
	protected static final String DATABASE_NAME = "paw";
	protected static final String DATABASE_USER = "paw";
	protected static final String DATABASE_PASSWORD = "paw";

	public static void executeInsertDeleteOrUpdateSql(String sqlStatement) {
		Statement statement = null;
		try {
			Connection connection = getNewConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sqlStatement);
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public static Connection getNewConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DATABASE_NAME, DATABASE_USER,
					DATABASE_PASSWORD);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return connection;
	}
	
	public static Long count(String tableName) throws SQLException {
		String sqlStatement = "SELECT COUNT(*) FROM " + tableName + ";";

		Connection connection = PostgresqlAdapter.getNewConnection();
		if (connection == null) {
			return new Long(-1);
		}

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(sqlStatement);
		
		if (!result.next()) {
			return new Long(-1);
		}

		Long count = result.getLong("count");

		result.close();
		statement.close();
		connection.close();
		return count;
	}
	
	public static void delete(Long id, String tableName) {
		String statement = "DELETE FROM " + tableName + " WHERE id = " + id.toString() + ";";
		executeInsertDeleteOrUpdateSql(statement);
		return;
	}
}
