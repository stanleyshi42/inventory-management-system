package inventoryManagementSystem;

import menu.LoginMenu;

public class Main {

	public static void main(String[] args) {
		DbInitializer.init();
		LoginMenu.run();
	}

}
