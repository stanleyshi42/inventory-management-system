package menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dao.ProductDAO;
import dao.UserDAO;
import model.*;

public class MainMenu {

	static private void printProducts() {
		ArrayList<Product> products = ProductDAO.getAllProducts();

		for (Product p : products) {
			System.out.println(p);
		}
	}

	static void adminMenuLoop(Scanner sc, User user) {
		// TODO
		System.out.println("ADMIN MODE");
		while (true) {
			System.out.println("1. ");
			System.out.println("2. ");
			System.out.println("3. ");

			System.exit(0);
		}
	}

	static void userMenuLoop(Scanner sc, User user) {

		System.out.println("Welcome " + user.username);
		while (true) {
			System.out.println("1. View products");
			System.out.println("2. Add products");
			System.out.println("3. Record sale");
			System.out.println("4. Logout");

			try {
				int input = sc.nextInt();
				sc.nextLine();

				// TODO added calls to each menu choice
				switch (input) {
				case 1:
					printProducts();
					continue;
				case 2:
					System.out.println("Called: " + input);
					continue;
				case 3:
					System.out.println("Called: " + input);
					continue;
				case 4:
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

	
}
