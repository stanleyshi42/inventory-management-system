package dao;

import java.sql.*;
import java.util.ArrayList;

import menu.Helper;
import model.Product;

public class ProductDAO {

	// Create queries
	public static boolean addProduct(float price, int quantity, String name) {

		if (price < 0 || quantity < 0)
			return false;

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO product (price, quantity, name) VALUES (?, ?, ?)");
			preparedStatement.setFloat(1, price);
			preparedStatement.setInt(2, quantity);
			preparedStatement.setString(3, name);
			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	// Read queries
	public static ArrayList<Product> getAllProducts() {

		ArrayList<Product> products = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM product");

			while (rs.next()) {
				int id = rs.getInt("id");
				float price = rs.getFloat("price");
				int quantity = rs.getInt("quantity");
				String name = rs.getString("name");
				products.add(new Product(id, price, quantity, name));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	public static Product getProduct(String name) {

		Product product = null;

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM product WHERE name = ?");
			preparedstatement.setString(1, name);
			ResultSet rs = preparedstatement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				float price = rs.getFloat("price");
				int quantity = rs.getInt("quantity");
				product = new Product(id, price, quantity, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;

	}

	public static Product getProduct(int id) {

		Product product = null;

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
			preparedstatement.setInt(1, id);
			ResultSet rs = preparedstatement.executeQuery();

			if (rs.next()) {
				float price = rs.getFloat("price");
				int quantity = rs.getInt("quantity");
				String name = rs.getString("name");
				product = new Product(id, price, quantity, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;

	}

	// Update queries
	public static boolean updateProduct(int id, float price, int quantity, String name) {

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			String query = """
					UPDATE product
					SET price = ?, quantity = ?, name = ?
					WHERE id = ?
					""";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setFloat(1, price);
			preparedStatement.setInt(2, quantity);
			preparedStatement.setString(3, name);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;

	}

	// Delete queries
}
