package com.shop.demoqa.testing;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shop.demoqa.testing.driver.DriverSingleton;
import com.shop.demoqa.testing.pages.LoginFeature;
import com.shop.demoqa.testing.pages.LogoutFeature;

public class TestLogout {
	
	public static WebDriver driver;
	private LogoutFeature logout;
	private LoginFeature login;
	String username = "testing98";
	String password = "TestingAccount*98";
	
	@BeforeClass
	public void setup() {
		DriverSingleton.getInstance("Chrome");
		driver = DriverSingleton.getDriver();
		String url = "https://shop.demoqa.com/my-account/";
		
		driver.get(url);
		login = new LoginFeature();
	}
	
	@BeforeMethod
	public void pageObject() {
		logout = new LogoutFeature();
	}
	
	@Test
	public void logout() {
		login.login(username, password);
		delay(3);
		logout.logout();
		delay(3);
		String actual = driver.getCurrentUrl();
		String expect = "https://shop.demoqa.com/my-account/";
		assertEquals(actual,expect);
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
