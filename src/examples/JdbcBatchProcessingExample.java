package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcBatchProcessingExample {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	public static void main(String[] args) {
		String insertSQL = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

			// Disable auto-commit mode
			conn.setAutoCommit(false);

			// Add multiple insert statements to the batch
			pstmt.setString(1, "John Doe");
			pstmt.setString(2, "HR");
			pstmt.setDouble(3, 50000.0);
			pstmt.addBatch();

			pstmt.setString(1, "Jane Smith");
			pstmt.setString(2, "Finance");
			pstmt.setDouble(3, 60000.0);
			pstmt.addBatch();

			pstmt.setString(1, "Sam Brown");
			pstmt.setString(2, "IT");
			pstmt.setDouble(3, 70000.0);
			pstmt.addBatch();

			// Execute the batch
			int[] affectedRecords = pstmt.executeBatch();

			// Commit the transaction
			conn.commit();

			System.out.println("Batch executed successfully. Number of records inserted: " + affectedRecords.length);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
