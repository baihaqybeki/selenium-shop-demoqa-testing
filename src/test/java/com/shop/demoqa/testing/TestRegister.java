package com.shop.demoqa.testing;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shop.demoqa.testing.driver.DriverSingleton;
import com.shop.demoqa.testing.pages.RegisterFeature;

public class TestRegister {
	
	public static WebDriver driver;
	private RegisterFeature register;
	String username = "testing98";
	String email = "testing98@mail.com";
	String password = "TestingAccount*98";
	
	@BeforeClass
	public void setup() {
		DriverSingleton.getInstance("Chrome");
		driver = DriverSingleton.getDriver();
		String url = "https://shop.demoqa.com/my-account/";
		
		driver.get(url);
	}
	
	@BeforeMethod
	public void pageObject() {
		register = new RegisterFeature();
	}
	
	@Test (priority = 1)
	public void validRegister() {
		register.navigateRegister();
		register.register(username,email,password);
	}
	
	@Test (priority = 2)
	public void invalidDuplicateAccount() {
		register.navigateRegister();
		register.register(username, email, password);
		delay(5);
		String actual = register.getMessage();
		assertTrue(actual.contains("already registered"));
	}
	
	@AfterMethod
	public void closeBrowser() {
		delay(3);
		driver.close();
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
