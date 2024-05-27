package com.dtf.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy (id="txtUserName")
	private WebElement userName;
	
	@FindBy (id="txtPassword")
	private WebElement Password;
	
	@FindBy (id="btnSignIn")
	private WebElement Login;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterValidCred(String Email, String password) {
		userName.sendKeys(Email);
		Password.sendKeys(password);
		Login.click();
	}
	
	public void enterInValidCred(String Email, String password) {
		userName.sendKeys(Email);
		Password.sendKeys(password);
		Login.click();
	}
	public void enterEmailAdd(String Email) {
		userName.sendKeys(Email);
		
	}
	public void enterPass(String password) {
		
		Password.sendKeys(password);
		
	}
	public AfterLoginPage clickOnLogin() {
		Login.click();
		return new AfterLoginPage(driver);
	}

}
