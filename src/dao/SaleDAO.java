package dao;

import java.sql.*;
import java.util.ArrayList;

import menu.Helper;
import model.Product;
import model.Sale;

public class SaleDAO {
	
	public static ArrayList<Sale> getAllSales() {

		ArrayList<Sale> sales = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM sale");

			while (rs.next()) {
				int id = rs.getInt("id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				Timestamp timestamp = rs.getTimestamp("timestamp");
				sales.add(new Sale(id, product_id, quantity, timestamp));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sales;
		
	}

	public static boolean addSale(int product_id, int quantity) {

		if (product_id < 0 || quantity < 0)
			return false;

		Product product = ProductDAO.getProduct(product_id);
		if (product == null) {
			System.out.println("Operation failed: product not found");
			return false;
		}
		if (quantity > product.quantity) {
			System.out.println("Operation failed: entered quantity exceeds product quantity");
			return false;
		}

		// Atomically record sale and deduct quantity from Product table
		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sale (product_id, quantity) VALUES (?, ?)");
			preparedStatement.setInt(1, product_id);
			preparedStatement.setInt(2, quantity);
			preparedStatement.executeUpdate();

			int newQuantity = product.quantity - quantity;

			preparedStatement = connection.prepareStatement("UPDATE product SET quantity = ? WHERE id = ?");
			preparedStatement.setInt(1, newQuantity);
			preparedStatement.setInt(2, product_id);
			preparedStatement.executeUpdate();

			connection.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Operation failed");
		return false;

	}
}
