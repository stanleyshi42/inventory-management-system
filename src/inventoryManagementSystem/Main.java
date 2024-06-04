package inventoryManagementSystem;

import menu.MainMenu;

public class Main {

	public static void main(String[] args) {
		DbInitializer.init();
		MainMenu menu = new MainMenu();
		menu.run();
	}

}
