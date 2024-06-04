package dao;

import java.sql.*;

public class UserDAO {

	final static String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
	final static String DB_USER = "root";
	final static String DB_PASSWORD = "root";

	// Checks login credentials
	public static boolean authenticateUser(String username, String password) {
		
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return false;

	}
}
