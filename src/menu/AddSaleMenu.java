package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.SaleDAO;

public class AddSaleMenu {

	public static void run(Scanner sc) {
		try {
			System.out.println("Enter product ID:");
			int idInput = sc.nextInt();
			sc.nextLine();

			System.out.println("Enter quantity:");
			int quantityInput = sc.nextInt();
			sc.nextLine();

			if (SaleDAO.addSale(idInput, quantityInput))
				System.out.println("Sale successfully recorded");

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
