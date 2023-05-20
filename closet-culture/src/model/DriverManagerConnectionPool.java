package model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:" + e.getMessage());
		}
	}

	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String url = "jdbc:mysql://localhost:3306/tsw?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root";
		String password = "amnp1029";
		try {

		newConnection = DriverManager.getConnection(url, username, password);
		newConnection.setAutoCommit(false);
		return newConnection;
		}catch (SQLException e) {
			if(newConnection != null)
				newConnection.close();
			
			newConnection = getConnection();
			return newConnection;
		}
		
	}

	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				if(connection != null)
					connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if (connection != null)
			freeDbConnections.add(connection);
	}
}
