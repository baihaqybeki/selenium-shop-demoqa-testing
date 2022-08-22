package com.shop.demoqa.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shop.demoqa.testing.driver.DriverSingleton;

public class LogoutFeature {
	
	private WebDriver driver;
	
	public LogoutFeature() {
		this.driver=DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	private WebElement btnLogout;
	
	public void logout() {
		btnLogout.click();
	}
}
