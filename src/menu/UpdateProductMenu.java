package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ProductDAO;

public class UpdateProductMenu {
	static void run(Scanner sc) {
		try {
			System.out.println("Enter product ID:");
			int idInput = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter new price:");
			float priceInput = sc.nextFloat();
			sc.nextLine();

			System.out.println("Enter new quantity:");
			int quantityInput = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter new name:");
			String nameInput = sc.nextLine();

			if (ProductDAO.updateProduct(idInput, priceInput, quantityInput, nameInput))
				System.out.println("Product successfully updated");
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
