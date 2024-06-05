package dao;

import java.sql.*;

import menu.Helper;
import model.User;

public class UserDAO {

	public static boolean addUser(String username, String password, String role) {

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username, password, role) VALUES (?, ?, ?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, role);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public static User getUser(String username) {

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
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
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
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
