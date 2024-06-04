package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Product;

public class ProductDAO {

	final static String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
	final static String DB_USER = "root";
	final static String DB_PASSWORD = "root";

	public static ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM product");

			while (rs.next()) {
				int id = rs.getInt("id");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String name = rs.getString("name");
				products.add(new Product(id, price, quantity, name));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return products;
	}

	public static Product getProduct(String name) {
		Product product = null;

		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM product WHERE name = ?");
			preparedstatement.setString(1, name);
			ResultSet rs = preparedstatement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				product = new Product(id, price, quantity, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return product;

	}

	public static Product getProduct(int id) {
		Product product = null;

		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
			preparedstatement.setInt(1, id);
			ResultSet rs = preparedstatement.executeQuery();

			if (rs.next()) {
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String name = rs.getString("name");
				product = new Product(id, price, quantity, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return product;

	}
}
