package model;

public class Product {
	public int id;
	public float price;
	public int quantity;
	public String name;
	public String description;

	public Product(int id, float price, int quantity, String name, String description) {
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
		this.description = description;
	}

}
