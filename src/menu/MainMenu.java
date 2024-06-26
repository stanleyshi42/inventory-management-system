package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ProductDAO;
import dao.SaleDAO;
import model.*;

public class MainMenu {

	static private void printProducts() {
		ArrayList<Product> products = ProductDAO.getAllProducts();

		if (products.size() == 0)
			System.out.println("No products to display");
		else
			for (Product p : products) {
				System.out.println(p);
			}
	}

	static private void printSales() {
		ArrayList<Sale> sales = SaleDAO.getAllSales();

		if (sales.size() == 0)
			System.out.println("No sales to display");
		else
			for (Sale s : sales) {
				System.out.println(s);
			}
	}

	static void adminMenuLoop(Scanner sc, User user) {

		System.out.println("ADMIN MODE");
		while (true) {
			System.out.println("1. View products");
			System.out.println("2. Search products");
			System.out.println("3. View sales record");
			System.out.println("4. Record sale");
			System.out.println("5. Add product");
			System.out.println("6. Update product");
			System.out.println("7. Delete product");
			System.out.println("8. Logout");

			try {
				int input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1:
					printProducts();
					System.out.println("Hit enter to return");
					sc.nextLine();
					continue;
				case 2:
					SearchProductMenu.run(sc);
					continue;
				case 3:
					AddSaleMenu.run(sc);
					continue;
				case 4:
					printSales();
					System.out.println("Hit enter to return");
					sc.nextLine();
					continue;
				case 5:
					AddProductMenu.run(sc);
					continue;
				case 6:
					UpdateProductMenu.run(sc);
					continue;
				case 7:
					DeleteProductMenu.run(sc);
					continue;
				case 8:
					return; // Return to login menu
				default:
					System.out.println("Invalid input");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine(); // Clear invalid input

			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	static void userMenuLoop(Scanner sc, User user) {

		System.out.println("Welcome " + user.username);
		while (true) {
			System.out.println("1. View products");
			System.out.println("2. Search products");
			System.out.println("3. View sales record");
			System.out.println("4. Record sale");
			System.out.println("5. Logout");

			try {
				int input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1:
					printProducts();
					System.out.println("Hit enter to return");
					sc.nextLine();
					continue;
				case 2:
					SearchProductMenu.run(sc);
					continue;
				case 3:
					AddSaleMenu.run(sc);
					continue;
				case 4:
					printSales();
					System.out.println("Hit enter to return");
					sc.nextLine();
					continue;
				case 5:
					return; // Return to login menu
				default:
					System.out.println("Invalid input");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input");
				sc.nextLine(); // Clear invalid input
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	static void run(Scanner sc, User user) {
		if (user != null && user.role.equals("admin"))
			adminMenuLoop(sc, user);
		else if (user != null)
			userMenuLoop(sc, user);
		else
			System.out.println("Error: invalid user");
	}

}
