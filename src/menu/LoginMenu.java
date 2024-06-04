package menu;

import java.util.Scanner;

import dao.UserDAO;
import model.User;

public class LoginMenu {
	
	public static void run() {
		
		Scanner sc = new Scanner(System.in);
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
						MainMenu.userMenuLoop(sc, user);
					} else if (user != null && user.role.equals("admin")) {
						MainMenu.adminMenuLoop(sc, user);
					}
				} else
					System.out.println("Login failed");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
