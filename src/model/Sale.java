package model;

import java.sql.Timestamp;

public class Sale {
	public int id;
	public int product_id;
	public int quantity;
	public Timestamp timestamp;

	public Sale(int id, int product_id, int quantity, Timestamp timestamp) {
		this.id = id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.timestamp = timestamp;
	}

}
