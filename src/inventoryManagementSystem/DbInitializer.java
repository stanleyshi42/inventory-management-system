package inventoryManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Product;

public class DbInitializer {

	final static String url = "jdbc:mysql://localhost:3306/inventory_management";
	final static String user = "root";
	final static String password = "root";

	public static void init() {
		initProductTable();
	}

	// Creates and populates the product table
	public static void initProductTable() {

		try {
			Connection connection = DriverManager.getConnection(url, user, password);

			// Create table
			Statement statement = connection.createStatement();

			statement.execute("DROP TABLE IF EXISTS product");

			statement.execute("""
					CREATE TABLE IF NOT EXISTS product (
						id INT PRIMARY KEY AUTO_INCREMENT,
						price INT NOT NULL,
						quantity INT NOT NULL,
						name VARCHAR(255) NOT NULL,
						description VARCHAR(255)
					)
					""");

			// Insert data into table
			ArrayList<Product> products = new ArrayList<>();
			products.add(new Product(0, 6.79f, 154, "Coffee Mug", "Big coffee mug, 16oz, ideal for coffee and tea. Fits most coffee machines. For all hot and cold beverages"));
			products.add(new Product(0, 39.99f, 42, "6 Pack Towels", "The pack comprises of 6 pool/gym/bath towels each measuring 24 by 48 inches"));
			products.add(new Product(0, 34.00f, 36, "Camping Chair", "Cozy design with convenient armrest cooler for leisurely lounging"));
			products.add(new Product(0, 25.95f, 20, "Electric Kettle", null));

			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (price, quantity, name, description) VALUES (?, ?, ?, ?)");
			for (int i = 0; i < products.size(); i++) {
				preparedStatement.setFloat(1, products.get(i).price);
				preparedStatement.setInt(2, products.get(i).quantity);
				preparedStatement.setString(3, products.get(i).name);
				preparedStatement.setString(4, products.get(i).description);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
