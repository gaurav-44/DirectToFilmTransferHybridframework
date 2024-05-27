package com.dtf.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter{
	
	public static ExtentReports genrateExtentReport(){
		
		ExtentReports extentreport=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\Extentreport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("Ignition Drawing test Automation Result");
		sparkReporter.config().setReportName("IGD Automation");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentreport.attachReporter(sparkReporter);
		
		Properties configProp=new Properties();
		File propFile1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dtf\\qa\\testdata\\testdata.properties");
		
		try {
		FileInputStream prop=new FileInputStream(propFile1);
        configProp.load(prop);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		extentreport.setSystemInfo("Application URL", (configProp.getProperty("url")));
		extentreport.setSystemInfo("Browser name", (configProp.getProperty("browser")));
		extentreport.setSystemInfo("Email", (configProp.getProperty("CustomerUserName")));
		extentreport.setSystemInfo("Password", (configProp.getProperty("password")));
		extentreport.setSystemInfo("Operation sysyetm", System.getProperty("os.name"));
		extentreport.setSystemInfo("System name", System.getProperty("user.name"));
		extentreport.setSystemInfo("java version", System.getProperty("java.version"));
		
		return extentreport;

		
	}

}
