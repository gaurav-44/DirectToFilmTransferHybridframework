package com.dtf.qa.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AfterLoginPage {
	WebDriver driver;
	
	@FindBy (id="LLReview")
	private WebElement LLGraph;
	
	public AfterLoginPage(WebDriver driver) {
		this.driver=driver;
	    PageFactory.initElements(driver, this);
	}
	
	public boolean verifyLLGraph() {
		boolean displaystatus=LLGraph.isDisplayed();
		return displaystatus;
	}
	
	public void waitUntilLLGraph() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
	}

}
