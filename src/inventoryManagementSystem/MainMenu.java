package inventoryManagementSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.UserDAO;
import model.*;

public class MainMenu {

	Scanner sc = new Scanner(System.in);

	private void adminMenuLoop(User user) {
		// TODO
		System.out.println("ADMIN MODE");
		while (true) {
			System.out.println("1. ");
			System.out.println("2. ");
			System.out.println("3. ");

			System.exit(0);
		}
	}

	private void userMenuLoop(User user) {

		System.out.println("Welcome " + user.username);
		while (true) {
			System.out.println("1. View products");
			System.out.println("2. Add products");
			System.out.println("3. Remove product");
			System.out.println("4. Record sale");
			System.out.println("5. Logout");

			try {
				int input = sc.nextInt();
				sc.nextLine();

				// TODO
				switch (input) {
				case 1:
					System.out.println("Called: " + input);
					break;
				case 2:
					System.out.println("Called: " + input);
					break;
				case 3:
					System.out.println("Called: " + input);
					break;
				case 4:
					System.out.println("Called: " + input);
					break;
				case 5:
					return;
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

	private void loginMenu() {
		while (true) {
			try {
				System.out.println("~~~Inventory Management System 9000~~~");
				System.out.println("Username:");
				String username = sc.nextLine();

				System.out.println("Password:");
				String password = sc.nextLine();

				// Authenticate and log in
				if (UserDAO.authenticateUser(username, password)) {
					User user = UserDAO.getUser(username);
					if (user != null && user.role.equals("user")) {
						userMenuLoop(user);
					} else if (user != null && user.role.equals("admin")) {
						adminMenuLoop(user);
					}
				} else
					System.out.println("Login failed");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		loginMenu();
	}
}
