package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import dao.ProductDAO;
import model.Product;

public class ProductDAOTest {

	@Test
	// Create, read, and delete a product
	public void testProductDAO() {

		// Test create
		float testPrice = 9.99f;
		int testQuantity = 11;
		String testName = "Test";

		assertTrue(ProductDAO.addProduct(testPrice, testQuantity, testName));

		// Test read
		ArrayList<Product> products = ProductDAO.getAllProducts();
		Product testProduct = products.get(products.size() - 1);

		assertEquals(testProduct.price, testPrice);
		assertEquals(testProduct.quantity, testQuantity);
		assertEquals(testProduct.name, testName);

		assertEquals(testProduct.id, ProductDAO.getProduct(testProduct.id).id);
		assertEquals(testProduct.id, ProductDAO.getProduct(testProduct.name).id);

		// Test delete
		assertTrue(ProductDAO.deleteProduct(testProduct.id));

	}

}
