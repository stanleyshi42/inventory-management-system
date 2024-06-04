package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ProductDAO;

public class DeleteProductMenu {

	static void run(Scanner sc) {
		try {
			System.out.println("Enter product ID:");
			int idInput = sc.nextInt();
			sc.nextLine();

			if (ProductDAO.getProduct(idInput) == null)
				System.out.println("Operation failed: product not found");
			else {
				if (ProductDAO.deleteProduct(idInput))
					System.out.println("Product successfully deleted");
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
