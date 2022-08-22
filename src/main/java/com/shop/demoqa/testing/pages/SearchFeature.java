package com.shop.demoqa.testing.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shop.demoqa.testing.driver.DriverSingleton;

public class SearchFeature {
	
	private WebDriver driver;
	
	public SearchFeature() {
		this.driver = DriverSingleton.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='noo-search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@name='s']")
	private WebElement searchInput;
	
	@FindBy(xpath = "//a[normalize-space()='pink drop shoulder oversized t shirt']")
	private WebElement result;
	
	@FindBy(xpath = "//p[@class='woocommerce-info']")
	private WebElement resultNull;
	
	public void searchItem(String item) {
		this.searchBtn.click();
		delay(3);
		this.searchInput.sendKeys(item, Keys.ENTER);
	}
	
	public String resultGetText() {
		return result.getText();
	}
	
	public Boolean resultNull() {
		return resultNull.isDisplayed();
	}
	
	static void delay(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
