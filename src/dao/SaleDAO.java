package dao;

import java.sql.*;

import menu.Helper;
import model.Sale;

public class SaleDAO {

	// TODO check if product ID is valid
	public static boolean addSale(int product_id, int quantity) {
		try {
			Connection connection = DriverManager.getConnection(Helper.DB_URL, Helper.DB_USER, Helper.DB_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sale (product_id, quantity) VALUES (?, ?)");
			preparedStatement.setInt(1, product_id);
			preparedStatement.setInt(2, quantity);
			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		return false;

	}
}
