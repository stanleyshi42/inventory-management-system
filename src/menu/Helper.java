package menu;

public class Helper {

	public final static String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
	public final static String DB_USER = "root";
	public final static String DB_PASSWORD = "root";

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
