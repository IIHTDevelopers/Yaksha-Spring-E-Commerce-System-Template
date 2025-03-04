package com.yaksha.assignment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ECommerceApplication {

	public static void main(String[] args) {
		// Load the Spring context from the XML configuration file
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Retrieve the beans from the Spring container
		Product product1 = context.getBean("product1", Product.class);
		Product product2 = context.getBean("product2", Product.class);
		Order order1 = context.getBean("order1", Order.class);

		// Display product and order details
		System.out.println("Product 1: " + product1.getName() + " priced at " + product1.getPrice());
		System.out.println("Product 2: " + product2.getName() + " priced at " + product2.getPrice());

		// Print order details
		System.out.println("Order ID: " + order1.getOrderId() + " Total: " + order1.getTotalAmount());

		// Simulate the order processing
		order1.addProduct(product1); // Add product 1 to the order
		order1.addProduct(product2); // Add product 2 to the order
	}
}
