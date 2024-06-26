package inventoryManagementSystem;

import java.sql.*;
import java.util.ArrayList;

import menu.Helper;
import model.*;

public class DbInitializer {

	// Call this to drop all tables and recreate them
	public static void main(String[] args) {
		init();
	}

	public static void init() {
		initProductTable();
		initUserTable();
		initSaleTable();
	}

	private static void initProductTable() {

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			Statement statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS product");

			statement.execute("""
					CREATE TABLE IF NOT EXISTS product (
						id INT PRIMARY KEY AUTO_INCREMENT,
						price FLOAT UNSIGNED NOT NULL,
						quantity INT UNSIGNED NOT NULL,
						name VARCHAR(255) NOT NULL
					)
					""");

			ArrayList<Product> products = new ArrayList<>();
			products.add(new Product(0, 699.99f, 24, "Computer"));
			products.add(new Product(0, 85.99f, 42, "Monitor"));
			products.add(new Product(0, 34.00f, 57, "Mouse"));
			products.add(new Product(0, 59.89f, 20, "Keyboard"));
			products.add(new Product(0, 45.59f, 20, "RAM"));

			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO product (price, quantity, name) VALUES (?, ?, ?)");
			for (int i = 0; i < products.size(); i++) {
				preparedStatement.setFloat(1, products.get(i).price);
				preparedStatement.setInt(2, products.get(i).quantity);
				preparedStatement.setString(3, products.get(i).name);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void initUserTable() {

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			Statement statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS user");

			statement.execute("""
					CREATE TABLE IF NOT EXISTS user (
						id INT PRIMARY KEY AUTO_INCREMENT,
						username VARCHAR(255) NOT NULL,
						password VARCHAR(255) NOT NULL,
						role VARCHAR(255) NOT NULL,
						UNIQUE (username)
					)
					""");

			ArrayList<User> users = new ArrayList<>();
			users.add(new User(0, "admin", "admin", "admin"));
			users.add(new User(0, "user", "user", "user"));

			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username, password, role) VALUES (?, ?, ?)");
			for (int i = 0; i < users.size(); i++) {
				preparedStatement.setString(1, users.get(i).username);
				preparedStatement.setString(2, users.get(i).password);
				preparedStatement.setString(3, users.get(i).role);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void initSaleTable() {

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			Statement statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS sale");

			statement.execute("""
					CREATE TABLE IF NOT EXISTS sale (
						id INT PRIMARY KEY AUTO_INCREMENT,
						product_id INT NOT NULL,
						quantity VARCHAR(255) NOT NULL,
						timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
					)
					""");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
