package com.shop.demoqa.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shop.demoqa.testing.driver.DriverSingleton;

public class RegisterFeature {
	
	private WebDriver driver;
	
	public RegisterFeature() {
		this.driver=DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='My Account']")
	private WebElement myAccountClick;
	
	@FindBy(id = "reg_username")
	private WebElement fieldUsernameReg;
	
	@FindBy(id = "reg_email")
	private WebElement fieldEmailReg;
	
	@FindBy(id = "reg_password")
	private WebElement fieldPassReg;
	
	@FindBy(xpath = "//button[@name='register']")
	private WebElement btnRegister;
	
	@FindBy(xpath = "//div[@id='primary']//li[1]")
	private WebElement errorMessage;
	
	public void navigateRegister() {
		boolean dismissBtn = driver.findElement(By.xpath("/html/body/p/a")).isDisplayed();
		if (dismissBtn==true) {
			driver.findElement(By.xpath("/html/body/p/a")).click();
		}
		myAccountClick.click();
	}
	
	public void register(String username,String email,String password) {
		this.fieldUsernameReg.sendKeys(username);
		this.fieldEmailReg.sendKeys(email);
		this.fieldPassReg.sendKeys(password);
		btnRegister.click();
	}
	
	public String getMessage() {
		return errorMessage.getText();
	}

}
