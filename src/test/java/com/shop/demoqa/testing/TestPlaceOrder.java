package com.shop.demoqa.testing;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shop.demoqa.testing.driver.DriverSingleton;
import com.shop.demoqa.testing.pages.LoginFeature;
import com.shop.demoqa.testing.pages.PlaceOrder;
import com.shop.demoqa.testing.pages.SearchFeature;

public class TestPlaceOrder {
	
	public static WebDriver driver;
	private LoginFeature login;
	private PlaceOrder order;		
	private SearchFeature search;
	String username = "testing98";
	String password = "TestingAccount*98";
	
	@BeforeClass
	public void setup() {
		DriverSingleton.getInstance("Chrome");
		driver = DriverSingleton.getDriver();
		String url = "https://shop.demoqa.com/my-account/";
		
		driver.get(url);
		login = new LoginFeature();
		login.login(username, password);
	}
	
	@BeforeMethod
	public void pageObject() {
		order = new PlaceOrder();
		search = new SearchFeature();
	}
	
	@Test (priority = 1)
	public void validOrder() {
		search.searchItem("PINK");
		order.detailProduct();
		order.checkOut();
		order.billingDetails();
		order.terms();
		order.placeOrder();
		
		String actual = order.getSuccessMessage();
		assertTrue(actual.contains("Your order has been received"));
	}
	
	@Test (priority = 2)
	public void nullBillingDetail() {
		search.searchItem("PINK");
		order.detailProduct();
		order.checkOut();
		order.terms();
		order.placeOrder();
		
		String actual = order.getErrorBillingMessage();
		assertTrue(actual.contains("is a required field"));
	}
	
	@Test (priority = 3)
	public void nullTerm() {
		search.searchItem("PINK");
		order.detailProduct();
		order.checkOut();
		order.billingDetails();
		order.placeOrder();
		
		String actual = order.getErrorTermMessage();
		assertTrue(actual.contains("is a required field"));
	}
	
	@Test (priority = 4)
	public void invalidTermAndBilling() {
		search.searchItem("PINK");
		order.detailProduct();
		order.checkOut();
		order.placeOrder();
		
		String actualBilling = order.getErrorBillingMessage();
		assertTrue(actualBilling.contains("is a required field"));
		
		String actualTerm = order.getErrorTermMessage();
		assertTrue(actualTerm.contains("is a required field"));
	}
	
	@AfterClass
	public void closeBrowser() {
		delay(3);
		driver.quit();
	}
	
	static void delay(int seconds) {
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
