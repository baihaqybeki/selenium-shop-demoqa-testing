package com.shop.demoqa.testing;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shop.demoqa.testing.driver.DriverSingleton;
import com.shop.demoqa.testing.pages.LoginFeature;
import com.shop.demoqa.testing.pages.LogoutFeature;

public class TestLogin {
	
	public static WebDriver driver;
	private LoginFeature login;
	private LogoutFeature logout;
	String username = "testing98";
	String password = "TestingAccount*98";
	String invalidUsername = "testing89";
	String invalidPassword = "AccountTesting89*";
	
	@BeforeClass
	public void setup() {
		DriverSingleton.getInstance("Chrome");
		driver=DriverSingleton.getDriver();

		String url = "https://shop.demoqa.com/my-account/";
		driver.get(url);
	}
	
	@BeforeMethod
	public void pageObject() {
		login = new LoginFeature();
		logout = new LogoutFeature();

	}
	
	@Test (priority = 1)
	public void validLogin() {
		login.login(username, password);
		logout.logout();
		
	}
	
	@Test (priority = 2)
	public void invalidUsername() {
		login.login(invalidUsername, password);
		delay(5);
		String actual = login.getMessage();
		assertTrue(actual.contains("is incorrect"));
	}
	
	@Test (priority = 3)
	public void invalidPassword() {
		login.login(username, invalidPassword);
		delay(5);
		String actual = login.getMessage();
		assertTrue(actual.contains("is incorrect"));
	}
	
	@Test (priority = 4)
	public void nullUsername() {
		login.login("", password);
		delay(5);
		String actual = login.getMessage();
		assertTrue(actual.contains("is required"));
	}
	
	@Test (priority = 5)
	public void nullPassword() {
		login.login(username, "");
		delay(5);
		String actual = login.getMessage();
		assertTrue(actual.contains("field is empty"));
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
