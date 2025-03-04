package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yaksha.assignment.Order;
import com.yaksha.assignment.Product;
import com.yaksha.assignment.utils.XMLParserUtils;

public class SpringContextControllerTest {

	@After
	public void afterAll() {
		testReport();
	}

	@Test
	public void testClassPathXmlContextLoadsProductAndOrderCorrectly() throws IOException {
		// Load the context from the classpath-based XML configuration
		ApplicationContext contextClasspath = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Retrieve the beans from the context
		Product product1 = contextClasspath.getBean("product1", Product.class);
		Product product2 = contextClasspath.getBean("product2", Product.class);
		Order order1 = contextClasspath.getBean("order1", Order.class);

		// Assert that the beans are correctly instantiated
		boolean productNotNull = product1 != null && product2 != null;
		boolean orderNotNull = order1 != null;

		// Assert that the 'name' and 'price' properties are set correctly for Product
		boolean productDetailsCorrect = "Laptop".equals(product1.getName()) && 1000.0 == product1.getPrice();
		boolean product2DetailsCorrect = "Smartphone".equals(product2.getName()) && 500.0 == product2.getPrice();

		// Assert that the 'orderId' and 'totalAmount' properties are set correctly for
		// Order
		boolean orderDetailsCorrect = "ORD12345".equals(order1.getOrderId()) && 0.0 == order1.getTotalAmount();

		// Use yakshaAssert to validate the test result
		yakshaAssert(currentTest(), productNotNull && orderNotNull && productDetailsCorrect && product2DetailsCorrect
				&& orderDetailsCorrect, businessTestFile);
	}

	@Test
	public void testApplicationContextXMLContainsRequiredBeanAndProperty() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml"; // Path to the XML file

		// Check if the 'product1' bean is defined and if the 'name' property is set to
		// "Laptop"
		boolean result = XMLParserUtils.checkXMLStructure(filePath, "product1", // The expected bean id in
				// applicationContext.xml
				"name", // The expected property name in the bean
				"Laptop"); // The expected property value

		// Using yakshaAssert to validate the test result
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	@Test
	public void testProductBeanPropertyFields() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml";

		// Check if the "name" property exists and has the correct value
		boolean namePropertyExists = XMLParserUtils.checkPropertyExists(filePath, "product1", "name", "Laptop");

		// Check if the "price" property exists and has the correct value
		boolean pricePropertyExists = XMLParserUtils.checkPropertyExists(filePath, "product1", "price", "1000.0");

		// Check if the 'scope' of the Product bean is 'prototype'
		boolean scopeIsPrototype = XMLParserUtils.checkBeanScope(filePath, "product1", "prototype");

		// Validate the results using yakshaAssert
		yakshaAssert(currentTest(), namePropertyExists && pricePropertyExists && scopeIsPrototype, businessTestFile);
	}

	@Test
	public void testOrderBeanPropertyFields() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml";

		// Check if the "orderId" property exists and has the correct value
		boolean orderIdPropertyExists = XMLParserUtils.checkPropertyExists(filePath, "order1", "orderId", "ORD12345");

		// Check if the "totalAmount" property exists and has the correct value
		boolean totalAmountPropertyExists = XMLParserUtils.checkPropertyExists(filePath, "order1", "totalAmount",
				"0.0");

		// Check if the 'scope' of the Order bean is 'singleton'
		boolean scopeIsSingleton = XMLParserUtils.checkBeanScope(filePath, "order1", "singleton");

		// Validate the results using yakshaAssert
		yakshaAssert(currentTest(), orderIdPropertyExists && totalAmountPropertyExists && scopeIsSingleton,
				businessTestFile);
	}

	@Test
	public void testMissingPropertyInXMLShouldFailTest() throws IOException {
		String filePath = "src/main/resources/applicationContext.xml"; // Path to the XML file

		// Check if the 'product1' bean has the 'price' property set to a non-existent
		// value
		boolean result = XMLParserUtils.checkXMLStructure(filePath, "product1", // The expected bean id in
				"price", // The expected property name in the bean
				"Non-Existent Value"); // A value that does not exist

		// Using yakshaAssert to validate the test result
		yakshaAssert(currentTest(), !result, businessTestFile);
	}
}
