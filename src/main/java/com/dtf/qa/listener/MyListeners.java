package com.dtf.qa.listener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.dtf.qa.utilities.ExtentReporter;

public class MyListeners implements ITestListener{
	ExtentReports extentreport;
	ExtentTest extentTest ;
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of project is started");
		extentreport=ExtentReporter.genrateExtentReport();
	}


	@Override
	public void onTestStart(ITestResult result) {
		//String testName=result.getMethod().getMethodName();
		String testName=result.getName();
		extentTest = extentreport.createTest(testName);
		extentTest.log(Status.INFO, testName+"started executing");
		System.out.println(testName+"  started executing");
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.PASS, testName+"got successfully executed");
		System.out.println(testName+"got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TakesScreenshot ts = ((TakesScreenshot)driver);
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		//File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String scrennshotPathString=System.getProperty("user.dir")+"\\Screenshot\\"+testName+".png";
		File srcScreenshotdestination=new File(scrennshotPathString);
		try {
			//FileHandler.copy(srcScreenshot,srcScreenshotdestination);
			FileUtils.copyFile(srcScreenshot,srcScreenshotdestination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(scrennshotPathString);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+"got failed");
		//String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		System.out.println(testName+"got failed");
		//System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		extentTest.log(Status.SKIP, testName+"got skipped");
		extentTest.log(Status.INFO, result.getThrowable());
	    System.out.println(testName+"got skipped");
		//System.out.println(result.getThrowable());
	}

	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution of project is Completed");
		extentreport.flush();
		
		String ExtentReport= System.getProperty("user.dir")+"\\test-output\\ExtentReport\\Extentreport.html";
		File report=new File(ExtentReport);
		try {
			Desktop.getDesktop().browse(report.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
