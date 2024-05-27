package com.dtf.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dtf.qa.basclass.BaseClass;
import com.dtf.qa.pageobject.AfterLoginPage;
import com.dtf.qa.pageobject.HomePage;
import com.dtf.qa.pageobject.LoginPage;
import com.dtf.qa.utilities.Utilities;

public class LoginTest extends BaseClass {
	public WebDriver driver;
	HomePage homepage;
	LoginPage loginpage;
	AfterLoginPage afterloginpage;
	
	public LoginTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		driver=initialiseBrowserAndopenTheApplication(prop.getProperty("browser"));
	}
    
	
	@AfterMethod
	public void  browserclose() {
		driver.quit();
	}
	@DataProvider
	public Object [] [] supplyTestData(){
		Object [] [] testData= {{"gloke@infinite-usa.com","test2000"},
				{"gloke@infinite-usa.com","test2000"},
				{"gloke@infinite-usa.com","test2000"}};
		return testData;
	}
	@DataProvider(name="credentialSupplier")
	public Object [] [] supplyTestData1(){
		Object [] [] excelTestData= Utilities.gettestdataFromExl("Login");
		
		return excelTestData;
	}
	@Test(priority = 4,dataProvider="credentialSupplier")
	public void verifyLoginWithExcelData(String Email,String password) {
		
		homepage=new HomePage(driver);
		homepage.orderHere();
		loginpage=homepage.loginPopup();
		//LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAdd(Email);
		loginpage.enterPass(password);
		afterloginpage=loginpage.clickOnLogin();
		//AfterLoginPage afterloginpage=new AfterLoginPage(driver);
		afterloginpage.waitUntilLLGraph();
		afterloginpage.verifyLLGraph();
		//driver.findElement(By.xpath("//ul[@id='IGDMasterNav']/li[8]/a/span")).click();
		//driver.findElement(By.xpath("(//button[@title='Login'])[2]")).click();
	    //driver.findElement(By.id("txtUserName")).sendKeys(Email);
	    //driver.findElement(By.id("txtUserName")).sendKeys("test");
		//driver.findElement(By.id("txtPassword")).sendKeys(password);
		//driver.findElement(By.id("btnSignIn")).click();
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
		//Assert.assertTrue(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
		
		
	}
	@Test(priority = 0,dataProvider="supplyTestData")
	public void verifyLoginWithValidCredWithData(String Email,String password) {
		
		homepage=new HomePage(driver);
		homepage.orderHere();
		loginpage=homepage.loginPopup();
		//driver.findElement(By.xpath("(//button[@title='Login'])[2]")).click();
		//LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAdd(Email);
		loginpage.enterPass(password);
		afterloginpage=loginpage.clickOnLogin();
		//AfterLoginPage afterloginpage=new AfterLoginPage(driver);
		afterloginpage.waitUntilLLGraph();
		afterloginpage.verifyLLGraph();
		Assert.assertTrue(afterloginpage.verifyLLGraph());
		/*
		 * driver.findElement(By.xpath("//ul[@id='IGDMasterNav']/li[8]/a/span")).click()
		 * ; driver.findElement(By.xpath("(//button[@title='Login'])[2]")).click();
		 * driver.findElement(By.id("txtUserName")).sendKeys(Email);
		 * driver.findElement(By.id("txtUserName")).sendKeys("test");
		 * driver.findElement(By.id("txtPassword")).sendKeys(password);
		 * driver.findElement(By.id("btnSignIn")).click(); WebDriverWait wait = new
		 * WebDriverWait(driver,Duration.ofSeconds(20));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
		 */
		
		
	}
	@Test(priority = 1)
	public void verifyLoginWithValidCred() {
		
		homepage=new HomePage(driver);
		homepage.orderHere();
		loginpage=homepage.loginPopup();
		//LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAdd(dataprop.getProperty("CustomerUserName"));
		loginpage.enterPass(dataprop.getProperty("password"));
		afterloginpage=loginpage.clickOnLogin();
		//AfterLoginPage afterloginpage=new AfterLoginPage(driver);
		afterloginpage.waitUntilLLGraph();
		//afterloginpage.verifyLLGraph();
		//Assert.assertTrue(driver.findElement(By.id("LLReview")).isDisplayed());
		Assert.assertTrue(afterloginpage.verifyLLGraph());
		/*
		 * driver.findElement(By.xpath("//ul[@id='IGDMasterNav']/li[8]/a/span")).click()
		 * ; driver.findElement(By.xpath("(//button[@title='Login'])[2]")).click();
		 * driver.findElement(By.id("txtUserName")).sendKeys(dataprop.getProperty(
		 * "CustomerUserName"));
		 * //driver.findElement(By.id("txtUserName")).sendKeys("test");
		 * driver.findElement(By.id("txtPassword")).sendKeys(dataprop.getProperty(
		 * "password")); driver.findElement(By.id("btnSignIn")).click(); WebDriverWait
		 * wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
		 */
		
		
	}
	@Test(priority = 2)
	public void verifyLoginWithInValidCredpurposely() {
		
		driver.findElement(By.xpath("//ul[@id='IGDMasterNav']/li[8]/a/span")).click();
		driver.findElement(By.xpath("(//button[@title='Login'])[2]")).click();
	    driver.findElement(By.id("txtUserName")).sendKeys(dataprop.getProperty("invalidCustomerUserName"));
		driver.findElement(By.id("txtPassword")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.id("btnSignIn")).click();
		String errorMessage=driver.findElement(By.id("lblLoginErr")).getText();
		Assert.assertEquals(errorMessage, dataprop.getProperty("expectederrorMessageWhileLogin"));
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
		 */
		
		
	}
	@Test(priority = 3)
	public void verifyLoginWithInValidCred() {
		
		driver.findElement(By.xpath("//ul[@id='IGDMasterNav']/li[8]/a/span")).click();
		driver.findElement(By.xpath("(//button[@title='Login'])[2]")).click();
	    driver.findElement(By.id("txtUserName")).sendKeys(Utilities.genratetimeStramo());
		driver.findElement(By.id("txtPassword")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.id("btnSignIn")).click();
		//String errorMessage=driver.findElement(By.id("lblLoginErr")).getText();
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LLReview")));
		Assert.assertTrue(driver.findElement(By.id("LLReview")).isDisplayed());
		 
		
	}

}
