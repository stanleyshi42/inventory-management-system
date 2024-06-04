package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ProductDAO;
import model.Product;

public class UpdateProductMenu {

	static void run(Scanner sc) {
		try {
			System.out.println("Enter product ID:");
			int idInput = sc.nextInt();
			sc.nextLine();

			Product product = ProductDAO.getProduct(idInput);

			if (product == null) {
				System.out.println("Operation failed: invalid ID");

			} else {
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

			}

		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
			sc.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Hit enter to return");
		sc.nextLine();

	}
}
