package dao;

import java.sql.*;

import model.User;

public class UserDAO {

	final static String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
	final static String DB_USER = "root";
	final static String DB_PASSWORD = "root";
	
	public static User getUser(String username) {
		
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("password");
				String role = rs.getString("role");
				return new User(id, username, password, role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

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
		}

		return false;

	}
}
