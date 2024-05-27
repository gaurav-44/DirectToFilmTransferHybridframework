package com.dtf.qa.basclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseClass {
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public BaseClass() {
		prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dtf\\qa\\config\\Config.properties");
		
		dataprop=new Properties();
		File propFile1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dtf\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream fis=new FileInputStream(propFile);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis1=new FileInputStream(propFile1);
			dataprop.load(fis1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public WebDriver initialiseBrowserAndopenTheApplication(String browserName) {
		//String browserName="Chrome";
		if (browserName.equals("Chrome")){
			ChromeOptions handlingSSL = new ChromeOptions();
			handlingSSL.setAcceptInsecureCerts(true);
			driver=new ChromeDriver(handlingSSL);
			//handlingSSL.setAcceptInsecureCerts(true);
			
		}
		else if (browserName.equals("firefox")) {
			driver=new FirefoxDriver();
			FirefoxOptions handlingSSL = new FirefoxOptions();
			handlingSSL.setAcceptInsecureCerts(true);
			driver=new FirefoxDriver(handlingSSL);
		}
		else if (browserName.equals("edge")) {
        	driver=new EdgeDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return driver;
	}

}
