package dao;

import java.sql.*;

import menu.Helper;
import model.Product;
import model.Sale;

public class SaleDAO {

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
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sale (product_id, quantity) VALUES (?, ?)");
			preparedStatement.setInt(1, product_id);
			preparedStatement.setInt(2, quantity);
			preparedStatement.addBatch();

			preparedStatement = connection.prepareStatement("UPDATE product SET quantity = ? WHERE id = ?");
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, product_id);
			preparedStatement.addBatch();

			preparedStatement.executeBatch();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Operation failed");
		return false;

	}
}
