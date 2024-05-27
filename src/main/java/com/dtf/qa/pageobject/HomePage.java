package com.dtf.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class HomePage{
	WebDriver driver;
	@FindBy (xpath="//ul[@id='IGDMasterNav']/li[8]/a/span")
	private WebElement orderHere;
	
	@FindBy (xpath="(//button[@title='Login'])[2]")
	private WebElement IGDLogin;
	
	

	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void orderHere() {
		orderHere.click();
		//IGDLogin.click();
	}
	public LoginPage loginPopup() {
		IGDLogin.click();
		return new LoginPage(driver);
	}

}
