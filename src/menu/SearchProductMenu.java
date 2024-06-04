package menu;

import java.util.Scanner;

import dao.ProductDAO;
import model.Product;

public class SearchProductMenu {

	public static void run(Scanner sc) {

		try {
			System.out.println("Enter a product name or ID to search for:");
			String input = sc.nextLine();

			Product result;
			if (isInteger(input))
				result = ProductDAO.getProduct(Integer.parseInt(input));
			else
				result = ProductDAO.getProduct(input);

			if (result != null)
				System.out.println(result);
			else
				System.out.println("Product not found");

			System.out.println("Hit enter to return");
			sc.nextLine();
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
