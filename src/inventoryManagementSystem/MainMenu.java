package inventoryManagementSystem;

import java.util.Scanner;

import dao.UserDAO;
import model.*;

public class MainMenu {

	Scanner sc = new Scanner(System.in);

	private void menuLoop(User user) {

		System.out.println("Welcome " + user.username);
		while (true) {
			System.out.println("1. ");
			System.out.println("1. ");
			System.out.println("1. ");
		}
	}

	private void loginMenu() {
		while (true) {
			try {
				System.out.println("Username:");
				String username = sc.nextLine();
				System.out.println("Password:");
				String password = sc.nextLine();
				boolean loggedIn = UserDAO.authenticateUser(username, password);
				System.out.println(loggedIn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		loginMenu();
	}
}
