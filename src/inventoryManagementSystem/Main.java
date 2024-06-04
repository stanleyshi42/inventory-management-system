package inventoryManagementSystem;

public class Main {

	public static void main(String[] args) {
		DbInitializer.init();
		MainMenu menu = new MainMenu();
		menu.run();
	}

}
