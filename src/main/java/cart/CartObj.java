package cart;

import java.io.Serializable;

public class CartObj implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String foodName;
	private int price;
	private int quantity;
	private String action;

	public CartObj(String foodName, int price, int quantity, String action) {
		this.foodName = foodName;
		this.price = price;
		this.quantity = quantity;
		this.action = action;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}