package model;

public class Product {
	public int id;
	public float price;
	public int quantity;
	public String name;

	public Product(int id, float price, int quantity, String name) {
		this.id = id;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", quantity=" + quantity + ", name=" + name + "]";
	}

}
