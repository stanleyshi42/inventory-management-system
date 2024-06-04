package inventoryManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import model.*;

public class DbInitializer {

	final static String url = "jdbc:mysql://localhost:3306/inventory_management";
	final static String user = "root";
	final static String password = "root";

	// Delete and set up all tables
	public static void init() {
		initProductTable();
		initUserTable();
		initSaleTable();
	}

	private static void initProductTable() {

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			// Create table
			Statement statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS product"); // TODO delete

			statement.execute("""
					CREATE TABLE IF NOT EXISTS product (
						id INT PRIMARY KEY AUTO_INCREMENT,
						price INT NOT NULL,
						quantity INT NOT NULL,
						name VARCHAR(255) NOT NULL,
						description VARCHAR(255)
					)
					""");

			// Insert into table
			ArrayList<Product> products = new ArrayList<>();
			products.add(new Product(0, 6.79f, 154, "Coffee Mug"));
			products.add(new Product(0, 39.99f, 42, "6 Pack Towels"));
			products.add(new Product(0, 34.00f, 36, "Camping Chair"));
			products.add(new Product(0, 25.95f, 20, "Electric Kettle"));

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
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS user");

			statement.execute("""
					CREATE TABLE IF NOT EXISTS user (
						id INT PRIMARY KEY AUTO_INCREMENT,
						username VARCHAR(255) NOT NULL,
						password VARCHAR(255) NOT NULL,
						role VARCHAR(255) NOT NULL
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
			Connection connection = DriverManager.getConnection(url, user, password);

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
