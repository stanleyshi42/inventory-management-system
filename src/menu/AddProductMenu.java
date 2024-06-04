package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ProductDAO;

public class AddProductMenu {
	static void run(Scanner sc) {
		try {
			System.out.println("Enter product name:");
			String nameInput = sc.nextLine();

			System.out.println("Enter price:");
			float priceInput = sc.nextFloat();
			sc.nextLine();

			System.out.println("Enter quantity:");
			int quantityInput = sc.nextInt();
			sc.nextLine();

			if (ProductDAO.addProduct(priceInput, quantityInput, nameInput))
				System.out.println("Product successfully added");
			else
				System.out.println("Operation failed");

			System.out.println("Hit enter to return");
			sc.nextLine();
			return;

		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
			sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
