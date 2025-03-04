package com.yaksha.assignment;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private String orderId;
	private double totalAmount;
	private List<Product> productList = new ArrayList<>();

	// Default constructor
	public Order() {
	}

	// Getters and Setters
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	// Add product to order
	public void addProduct(Product product) {
		this.productList.add(product);
		this.totalAmount += product.getPrice();
	}
}
